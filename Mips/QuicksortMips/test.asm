.data 0x0
newline:        .asciiz "\n"
.text 0x3000
.globl main

main:
body:
  	li $v0, 5
  	syscall
     	move $s1, $v0  	
     	
     	li $v0, 1
     	move $a0, $s1
     	syscall
     	
     	li $v0, 4
        li $a0, 0
        syscall
  	jal initializeArray

  	li $a0, 4
  	move $a1, $0
  	addi $a2, $s1, -1
  	jal qSort
  	
	jal printArray
	j end	

initializeArray:
 li $t0, 0
 addi $t1, $s1, -1
 
 initializeArrayLoop:
 sll $t3, $t0, 2
 
 li $v0, 5
 syscall
 move $t2, $v0 
 
 sw $t2, 4($t3)
 beq $t0, $t1, initializeArrayExit
 addi $t0, $t0, 1
 j initializeArrayLoop

initializeArrayExit: 
jr $ra

 printArray:
 li $t0, 0
 addi $t1, $s1, -1
 
 printArrayLoop:
 sll $t3, $t0, 2
 
 lw $a0, 4($t3)
  
 li $v0, 1
 syscall
 
 beq $t0, $t1, printArrayExit
 li $v0, 4
 li $a0, 0
 syscall
 addi $t0, $t0, 1
 j printArrayLoop
 
 printArrayExit:
 jr    $ra

qSort:
    bgt     $a1, $a2, qSortReturn    
    
    subu    $sp, $sp, 16
    sw      $ra, 16($sp)
    sw      $a0, 12($sp)
    sw      $a1, 8($sp)
    sw      $a2, 4($sp)   
    jal subArray        

    subu    $sp, $sp, 4
    sw      $v0, 4($sp)   
    lw      $a0, 16($sp)      
    lw      $a1, 12($sp)      
    addi    $a2, $v0, -1      
    jal qSort

    lw      $a0, 16($sp)  
    lw      $t0, 4($sp)
    addi    $a1, $t0, 1   
    lw      $a2, 8($sp)   
    jal qSort

    addu $sp, $sp, 20
    lw $ra, 0($sp)    

qSortReturn: 
jr $ra 

subArray:
    sll $t1, $a1, 2
    add $t1, $t1, $a0     
    lw  $t2, 0($t1)     
    addi $t3, $a1, 1      
    add $t4, $a2, $0      

whileLowThanLessUp:
blt $t4, $t3, subArrayReturn

    lowerComp:
    sll     $t8, $t3, 2
    add     $t8, $t8, $a0      
    lw      $t5, 0($t8)      
    ble     $t5, $t2, lBoundCheck
    j       upperComp
    lBoundCheck:
    ble     $t3, $t4, lowerInc
    j       upperComp
    lowerInc:
    addi    $t3, $t3, 1
    j       lowerComp

    upperComp:
    sll     $t8, $t4, 2
    add     $t8, $t8, $a0      
    lw      $t5, 0($t8)       
    bgt     $t5, $t2, upperDec
    j       swap

    upperDec:
    addi    $t4, $t4, -1
    j       upperComp

swap:
    bgt     $t3, $t4, whileLowThanLessUp
    sll     $t8, $t3, 2
    add     $t8, $t8, $a0
    sll     $t9, $t4, 2
    add     $t9, $t9, $a0     
    lw      $t6, 0($t8)            
    lw      $t7, 0($t9)       
    sw      $t7, 0($t8)       
    sw      $t6, 0($t9)       
    addi    $t3, $t3, 1
    addi    $t4, $t4, -1
j whileLowThanLessUp

subArrayReturn:
    sll     $t8, $t4, 2
    add     $t8, $t8, $a0  
    sll     $t9, $a1, 2
    add     $t9, $t9, $a0          
    lw      $t2, 0($t9)       
    lw      $t3, 0($t8)       
    sw      $t3, 0($t9)       
    sw      $t2, 0($t8)       
    addi    $v0, $t4, 0       
    jr      $ra

end:
ori     $v0, $0, 10 
syscall
