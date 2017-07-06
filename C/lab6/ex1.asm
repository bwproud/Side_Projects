#---------------------------------
# Lab 6: Pixel Conversion
#
# Name: Brennan Proudfoot
#
#---------------------------------

.data 0x0
	new:	        .asciiz	"\n"
	startString:	.asciiz	"P2\n"
	finishString:	.asciiz	"Finished."
	newline:	.asciiz	"\n"
	msg: 		.asciiz "Text: "
	buffer: 	.space  4  
.text 0x3000

main:
	addi $t1, $0, 100
	addi $t2, $0, 255
	addi $t7, $0, 30
	addi $t8, $0, 59
	addi $t9, $0, 11
	
	li $v0, 8
  	la $a0, buffer
  	li $a1, 4
  	syscall
  	
  	li $v0, 5
  	syscall
     	move $s1, $v0  	
     	
  	li $v0, 5
  	syscall
     	move $s2, $v0 
  	
  	li $v0, 5
  	syscall
     	move $s3, $v0 
  	
	ori $v0, $0, 4				
	ori $a0, $0, 2   	 		
	syscall	
	
	ori $v0, $0, 1				
	add $a0, $0, $s1   	 		
	syscall	
	ori $v0, $0, 4				
	la $a0, new
	syscall
	ori $v0, $0, 1				
	add $a0, $0, $s2   	 		
	syscall	
	ori $v0, $0, 4				
	la $a0, new  	 		
	syscall
	ori $v0, $0, 1				
	addi $a0, $0, 255   	 		
	syscall	
	
	mult $s1, $s2
	mflo $s4
	
	add $s5, $0, $0 
	mult $s3, $t1
     	mflo $s3				
loop:  
	beq $s5, $s4, exit
	
	ori $v0, $0, 4				
	la $a0, new  	 		
	syscall	
	
	li $v0, 5
  	syscall
     	move $t4, $v0  	
     	
  	li $v0, 5
  	syscall
     	move $t5, $v0 
  	
  	li $v0, 5
  	syscall
     	move $t6, $v0
     
        mult $t4, $t7
        mflo $t4
        mult $t5, $t8
        mflo $t5
        mult $t6, $t9
        mflo $t6
     	
     	add $t3, $t4, $0
     	add $t3, $t3, $t5
     	add $t3, $t3, $t6
     	mult $t3, $t2
     	mflo $t3
     	div $t3, $s3
     	mflo $t3
     	
     	ori $v0, $0, 1				
	add $a0, $0, $t3   	 		
	syscall
	
	addi $s5, $s5, 1
     	j loop
exit:						
	ori $v0, $0, 10				
	syscall						

