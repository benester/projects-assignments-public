  # timetemplate.asm
  # Written 2015 by F Lundevall
  # Copyright abandonded - this file is in the public domain.

.macro	PUSH (%reg)
	addi	$sp,$sp,-4
	sw	%reg,0($sp)
.end_macro

.macro	POP (%reg)
	lw	%reg,0($sp)
	addi	$sp,$sp,4
.end_macro

	.data
	.align 2
mytime:	.word 0x5957
timstr:	.ascii "text more text lots of text\0"
	.text
main:
	# print timstr
	la	$a0,timstr
	li	$v0,4
	syscall
	nop
	# wait a little
	li	$a0,1000
	jal	delay
	nop
	# call tick
	la	$a0,mytime
	jal	tick
	nop
	# call your function time2string
	la	$a0,timstr
	la	$t0,mytime
	lw	$a1,0($t0)
	jal	time2string
	nop
	# print a newline
	li	$a0,10
	li	$v0,11
	syscall
	nop
	# go back and do it all again
	j	main
	nop
# tick: update time pointed to by $a0
tick:	lw	$t0,0($a0)	# get time
	addiu	$t0,$t0,1	# increase
	andi	$t1,$t0,0xf	# check lowest digit
	sltiu	$t2,$t1,0xa	# if digit < a, okay
	bnez	$t2,tiend
	nop
	addiu	$t0,$t0,0x6	# adjust lowest digit
	andi	$t1,$t0,0xf0	# check next digit
	sltiu	$t2,$t1,0x60	# if digit < 6, okay
	bnez	$t2,tiend
	nop
	addiu	$t0,$t0,0xa0	# adjust digit
	andi	$t1,$t0,0xf00	# check minute digit
	sltiu	$t2,$t1,0xa00	# if digit < a, okay
	bnez	$t2,tiend
	nop
	addiu	$t0,$t0,0x600	# adjust digit
	andi	$t1,$t0,0xf000	# check last digit
	sltiu	$t2,$t1,0x6000	# if digit < 6, okay
	bnez	$t2,tiend
	nop
	addiu	$t0,$t0,0xa000	# adjust last digit
tiend:	sw	$t0,0($a0)	# save updated result
	jr	$ra		# return
	nop

  # you can write your code for subroutine "hexasc" below this line
  #
  
  hexasc: 
  	li $v0, 0
	andi $t0, $a0, 0x0f  #maskar ut de l�gsta 4 bitarna
	ble  $t0, 0x9, siffra
	nop
	#bokst�ver
	addi $v0, $t0, 0x37 #37 d� minsta bokstaven har v�rdet 10 och assci koden f�r bokst�ver �r 0x41
	jr $ra
	nop
	
siffra: 
	addi $v0, $t0, 0x30  #siffran 0 har assci kod 0x30
	jr $ra
	nop
	
delay:
	PUSH($ra)
	while:
	bge $0, $a0, exit
	nop
	addi $a0, $a0, -1
	addi $t0, $0, 0  #var i
		for:
		addi $t0, $t0, 1
		blt $t0, 600, for
		nop
		j while
		nop
	
	exit:
	POP($ra)
	jr $ra
 	nop
 	
 time2string: 
 	PUSH($ra)
 	addi $t4, $0, 0x3a  # : i assci
 	addi $t5, $0, 0x00  #null byte
 	
 	PUSH($a0)			#59:5(8) denna del hanterar 8an
 	move $a0, $a1			#Flyttar a1 till a0, d� hexasc anv�nder argument registret a0
 	jal hexasc
 	nop
 	POP($a0)
 	sb $v0 , 4($a0)
 	
 	
 	PUSH($a0)			#59:(5)8
 	sra $a0, $a1, 4			#Logig shift, f�r att hantera n�sta siffra i tiden. 4 d� varje siffra �r kodad i 4 bitar
 	jal hexasc
 	nop
 	POP($a0)
 	sb $v0 , 3($a0)
 	
 	sb $t4, 2($a0)  #sparar kolon i a0	59(:)58
 	
 	PUSH($a0)	#5(9):58
 	sra $a0, $a1, 8
 	jal hexasc
 	nop
 	POP($a0)
 	sb $v0 , 1($a0)
 	
 	PUSH($a0)	#(5)9:58
 	srl $a0, $a1, 12
 	jal hexasc
 	nop
 	POP($a0)
 	sb $v0 , 0($a0)

 	sb $t5, 5($a0) #Null, f�r slutet	59:58(00)
 	
 	POP($ra) #tar ut return adressen fr�n stacken, som lades in f�rst
 	jr $ra
 	nop

