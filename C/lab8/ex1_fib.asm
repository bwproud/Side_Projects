# =============================================================
# PROCEDURE WRITING WORKSHEET
#
# How many arguments does this procedure expect?
#						 ANSWER: 1
# Where are they going to be?  
#			      ANSWER: in $a0
# Write the code for the body of this procedure first,
#   without worrying much about saving/restoring values
#   on/from the stack
#
# Which registers out of $s0-$s7 does this procedure modify?
#								ANSWER: none
# Which registers out of $t0-$t9, $a0-$a3, and $v0-$v1 must
#   be protected from a call to a callee?
#							ANSWER: $a0
# Which local variables are needed to be created?
#							ANSWER: none
# What is the max number of arguments this procedure will need
#   for calling *any* callee?
#							ANSWER: one
# Now, complete your code using the template below
#
# =============================================================

.globl fibonacci
fibonacci:
	bgt	$a0, 1, fib_recurse
	move	$v0, $a0
	jr	$ra
fib_recurse:
	sub	$sp, $sp, 12
	sw	$ra, 0($sp)
	
	sw	$a0, 4($sp)
	add	$a0, $a0, -1
	jal	fibonacci
	lw	$a0, 4($sp)
	sw	$v0, 8($sp)
	
	add	$a0, $a0, -2
	jal	fibonacci
	
	lw	$t0, 8($sp)
	add	$v0, $t0, $v0
	
	lw	$ra, 0($sp)
	add	$sp, $sp, 12
	jr	$ra
