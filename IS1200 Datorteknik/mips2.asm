.text
jal main
stop: j stop

main:
	addiu $t1, $0, 100
	sltiu $t0,$t1,0x1
	j foo
	addiu $t1, $0, 100
	addiu $t1, $0, 100
	foo:
	j stop