#---------------------------------
# Lab 6: Pixel Conversion
#
# Name: Brennan Proudfoot
#
# --------------------------------
# Below is the expected output.
# 
# Converting pixels to grayscale:
# 0
# 1
# 2
# 34
# 5
# 67
# 89
# Finished.
# -- program is finished running --
#---------------------------------

.data 0x0
	new:	        .asciiz	"\n"
	startString:	.asciiz	"Converting pixels to grayscale:\n"
	finishString:	.asciiz	"Finished."
	newline:	.asciiz	"\n"
	blue:		.asciiz	"blue: "
	green:		.asciiz	"red: "
	red:		.asciiz	"green: "
	sum:		.asciiz "sum: "
	division:	.asciiz "division: "
	pixels:		.word 	0x00010000,	0x010101,	0x6,		0x3333, 
				0x030c,		0x700853,	0x294999,	-1

.text 0x3000

main:
	ori $v0, $0, 4				
	ori $a0, $0, 2   	 		
	syscall						
        add $t6, $t6, $0
loop:
     	add   $20, $0, $0
    	add   $t4, $0, $0
  	add   $16, $0, $0
   	sll   $10, $t6, 2     
  	lw    $10, pixels($10)  
  	beq   $10, -1, exit
 	addi  $16, $16, 0xFF
        and   $17, $16, $10
  	srl   $10, $10, 8
     	and   $18, $16, $10
     	srl   $10, $10, 8
     	and   $19, $16, $10
     	add   $20, $20, $17
     	add   $20, $20, $18
     	add   $20, $20, $19
     	addi  $t4, $t4, 3
     	div   $t5, $20, $t4
     	mflo  $t5
     	li $v0, 1				
     	add $a0, $t5, $0	 		
     	syscall	
     	ori $v0, $0, 4				
     	ori $a0, $0, 0x0   	 		
     	syscall		
     	addi  $t6, $t6, 1    
     	j loop
exit:
	ori $v0, $0, 4				
	ori $a0, $0, 35  			
	syscall						
	ori $v0, $0, 10				
	syscall						
