  # labwork.S
  # Written 2015-2017 by F Lundevall
  # Skeleton file for IS1200/IS1500 lab 1.
  # The macros PUSH and POP are in the public domain.
  # Please add your own code at the end of the file.
	.global delay
	.global time2string
  #
  # Please keep the two macros PUSH and POP unchanged
  #
.macro	PUSH reg
	addi	$sp,$sp,-4
	sw	\reg,0($sp)
.endm

.macro	POP reg
	lw	\reg,0($sp)
	addi	$sp,$sp,4
.endm
  #
  # Please add your own code below this line
  #
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
	andi $t0, $a0, 0x0f  #maskar ut de lägsta 4 bitarna
	ble  $t0, 0x9, siffra
	nop
	#bokstäver
	addi $v0, $t0, 0x37 #37 då minsta bokstaven har värdet 10 och assci koden för bokstäver är 0x41
	jr $ra
	nop
	
siffra: 
	addi $v0, $t0, 0x30  #siffran 0 har assci kod 0x30
	jr $ra
	nop
	
delay:
	PUSH $ra
	while:
	bge $0, $a0, exit    #branch if greater or equal
	nop
	addi $a0, $a0, -1
	addi $t0, $0, 0  #var i
		for:
		addi $t0, $t0, 1
		blt $t0, 6500, for
		nop
		j while
		nop
	
	exit:
	POP $ra
	jr $ra
 	nop
 	
 time2string: 
 	PUSH $ra
 	addi $t4, $0, 0x3a  # (:) i assci
 	addi $t5, $0, 0x00  #null byte
 	
 	PUSH $a0			#59:5(8) denna del hanterar 8an
 	move $a0, $a1			#Flyttar a1 till a0, då hexasc använder argument registret a0
 	jal hexasc
 	nop
 	POP $a0
 	sb $v0 , 4($a0)
 	
 	
 	PUSH $a0			#59:(5)8
 	sra $a0, $a1, 4			#Logig shift, för att hantera nästa siffra i tiden. 4 då varje siffra är kodad i 4 bitar
 	jal hexasc
 	nop
 	POP $a0
 	sb $v0 , 3($a0)
 	
 	sb $t4, 2($a0)  #sparar kolon i a0	59(:)58
 	
 	PUSH $a0	
 	sra $a0, $a1, 8
 	jal hexasc
 	nop
 	POP $a0
 	sb $v0 , 1($a0)
 	
 	PUSH $a0
 	srl $a0, $a1, 12
 	jal hexasc
 	nop
 	POP $a0
 	sb $v0 , 0($a0)

 	sb $t5, 5($a0) #Null, för slutet	59:58(NULL)
	
	#SUPRISE UPG
	#Hämtar klockslaget
	lb $t4, 4($a0)		#Hämtar 59:5(8)
	lb $t3, 3($a0)		#Hämtar 59:(5)8
	lb $t1, 1($a0)		#Hämtar 5(9):58
	lb $t0, 0($a0)		#Hämtar (5)9:58
	
	#Kollar om det står 00:00
	addi $t6, $0, 0x30 	#Jämför med asciikoden för 0.
	bne $t4, $t6, skip	#Om $t4 != 0x30 (aka 0 i ascii)
	bne $t3, $t6, skip	
	bne $t1, $t6, skip
	bne $t0, $t6, skip
	
	
	addi $t4, $0, 0x00	#NULL
	addi $t3, $0, 0x52	#R
	addi $t2, $0, 0x55	#U
	addi $t1, $0, 0x4f	#O
	addi $t0, $0, 0x48	#H
	
	sb $t4, 4($a0)
	sb $t3, 3($a0)
	sb $t2, 2($a0)
	sb $t1, 1($a0)
	sb $t0, 0($a0)
	
 skip:
 	POP $ra #tar ut return adressen från stacken, som lades in först
 	jr $ra
 	nop

