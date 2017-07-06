#---------------------------------
# Lab 6: Pixel Conversion
#
# Name: Brennan Proudfoot
#
#---------------------------------

.data 0x0
	new:	        .asciiz	"\n"
	startString:	.asciiz	"P2\n"
	buffer: 	.space  4  
.text 0x3000

main:	
  	
  	li $v0, 5
  	syscall
     	move $s0, $v0  	
     	
  	li $v0, 5
  	syscall
     	move $s1, $v0 
  	
  	li $v0, 5
  	syscall
     	move $s2, $v0
     	
     	li $v0, 5
  	syscall
     	move $s3, $v0
     	
     	li $v0, 5
  	syscall
     	move $s4, $v0 
     	
     	li $v0, 5
  	syscall
     	move $s5, $v0 
     	
     	li $v0, 5
  	syscall
     	move $s6, $v0   
  	
  	sub $s7, $s1, $s0
  	addi $s7, $s7, 1
  	
  	sub $t8, $s3, $s2
  	addi $t8, $t8, 1
  	
	ori $v0, $0, 4				
	ori $a0, $0, 2   	 		
	syscall	
	
	ori $v0, $0, 1				
	add $a0, $0, $s7   	 		
	syscall
		
	ori $v0, $0, 4				
	la $a0, new
	syscall
	
	ori $v0, $0, 1				
	add $a0, $0, $t8   	 		
	syscall	
	
	ori $v0, $0, 4				
	la $a0, new  	 		
	syscall
	
	ori $v0, $0, 1				
	addi $a0, $0, 255   	 		
	syscall	
	
	addi $t0, $0, -1
	addi $s5, $s5, -1
	add $t1, $0, $0	
for1: 
	beq $t0, $s5, exit
	addi $t0, $t0, 1
	li $t1, 0
     	j for2

for2:
	beq $t1, $s4, for1
	
	
	li $v0, 5
  	syscall
     	move $t4, $v0
	
	slt $t5, $t1, $s0
	slt $t6, $s1, $t1
	slt $t7, $t0, $s2
	slt $t2, $s3, $t0
	li $t3, 0
	add $t3, $t3, $t5
	add $t3, $t3, $t6
	add $t3, $t3, $t7
	add $t3, $t3, $t2
	beq $t3, $0, print
	
	addi $t1, $t1, 1
     	j for2
print:
	ori $v0, $0, 4				
	la $a0, new  	 		
	syscall
	ori $v0, $0, 1				
	add $a0, $0, $t4   	 		
	syscall
	addi $t1, $t1, 1
	j for2
exit:						
	ori $v0, $0, 10				
	syscall						

