
.data 0x0
newline:        .asciiz "\n"
buffer: 	.space  21
.text 0x3000
.globl main

main:
ori     $sp, $0, 0x2ffc    
addi    $fp, $sp, -4 


body:

li $v0, 8
la $a0, buffer
li $a1, 21
syscall

jal a_to_i
	
move $a0, $v0
ori  $v0, $0, 1 
syscall
 
beq $a0, $0, end

ori $v0, $0, 4
li $a0, 0
syscall

j body


a_to_i:
addi    $sp, $sp, -8        
sw  $ra, 4($sp)            
sw  $fp, 0($sp)            
addiu   $fp, $sp, 4                    
li $t0, 0
pbody:
lb $t2, 0($a0)
slti $t3, $t2, 48
beq $t3, 1, return_from_proc1
addi $t2, $t2, -48
mul $t0, $t0, 10
add $t0, $t0, $t2
addi $a0, $a0, 1
j pbody
			
return_from_proc1:
add $v0, $0, $t0
lw  $ra, 0($fp)             
lw  $fp, -4($fp)            
addiu   $sp, $sp, 8        
jr  $ra                     


end:
ori     $v0, $0, 10 
syscall     
           
