
.text 
	addi $a0, $0, 8   #input för n!
	addi $a1, $0 ,1	  #Håller koll på index
	addi $a3, $0, 2	  #Håller koll på index
	add $a2, $0, 1
	add $v1, $0, $a0  #Sätter första plus värdet till n
	beq $a0, $0, fall0 
loop: 
	add $v0, $v0, $v1  
	
	beq $a0, $a1, loop2 #för fall där n < 2
	add $a1, $a1, 1  

	beq $a0, $a1, loop2
	beq $0, $0, loop 
loop2:
	beq $a3, $a0, done #för fall där n<3
	beq $a2, $a0, done #för fall där n =1 
	
	add $a3, $a3, 1
	add $a1, $0, $a3
	add $v1, $v0, $0
	
	beq $a1, $a0, done
	beq $0, $0, loop
fall0:
	addi $v0, $0, 1
done: 
beq $0,$0, done 