.data
.align 2

factlist: 
	.space 32
	.text
	.globl makelist #För att göra makelist callable från andra filer.
	jal main
stop: 
	j stop
fact:
	addi $v0, $0,1
factloop:
	ble $a0, $0, donefact
	mul $v0, $v0, $a0
	addi $a0, $a0, -1
	j factloop
donefact:
	jr $ra
makelist:
	PUSH($ra)
	PUSH($s0)
	PUSH($s1)
	PUSH($s2)
	PUSH($s3)

	move $s0,a$0
	move $s1, $a1
	addi $s2, $0,0
	la $s3, factlist
mateloop: slt $t0, $s2, $s1
	beq $t0, 0, mateend
	move $a0, $s0
	jal fact
	sll $t0, $s0, 2
	add $t1, $s3, $t0
	sw $v0, 0($t1)
	addi $s0, $s0, 1
	addi $s2, $s2, 1
	j mateloop
mateend: 
	POP($s3)
	POP($s2)
	POP($s1)
	POP($s0)
	POP($ra)
	jr $ra
main: 
	PUSH($ra)
	addi $a0, $0, 3
	addi $a1 ,$0, 8 
	jal makelist
	POP($ra)
	jr $ra