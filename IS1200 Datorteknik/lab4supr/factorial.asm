
.text

addi $a0, $0, 0

beq $0, $a0, caseZero

add $v0, $0, $a0
addi $a1, $a0, -1

loop:
beq $a1, $0, forever
mul $v0, $v0, $a1
addi $a1, $a1, -1
beq $0, $0, loop

caseZero:
addi $v0, $0, 1

forever:
beq $0, $0, forever
