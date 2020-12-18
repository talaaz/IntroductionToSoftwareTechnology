//OVERVIEW
//R0 - n input
//R1 - pop(n) input/output
//R16 - @POP1. Has the value of pop(n-1)
//R17 - @POP2. Has the value of pop(n-2)
//R18 - @CONST5. Has a constant value of 5. Used to multiply
//R19 - @CONST2. Has a constant value of 2. Used to division
//R20 - @SUM Used to hold the result of mathoperations.
//R21 - @COUNT Count also holds some results of a mathoperation, as it's the count that is the result in forexample division

(INIT)
	//Check if n is <2
	@R0
	D=M
	@PREEND
	D-1;JLE
	//Initialize variables
	@POP1
	M=1
	@POP2
	M=0
	@5
	D=A
	@CONST5
	M=D
	@2
	D=A
	@CONST2
	M=D

(LOOP)
	//N--; for each loop, to know when to stop
	@R0
	M=M-1
	D=M
	@END
	D;JEQ

//MULTIPLICATION
	//Reset sum value from previous runs
	@SUM
	M=0
	//Make our factor pop(n-1)
	@POP1
	D=M
	@COUNT
	M=D
(MULLOOP)
	//Check if count is 0  
	@COUNT
	D=M  
	@DIVISION
	D;JEQ
	//Select our constant and add it to our sum
	@CONST5
	D=M 
	@SUM
	M=M+D 
	//Count--;
	@COUNT
	M=M-1 
	@MULLOOP
	0;JMP

(DIVISION)
	//Reset count
	@COUNT
	M=0
	//Get sum
	@SUM
	D=M 
(DIVLOOP)
	//Check if sum is either 1 or 0, if so, it cant be diveded by 2 anymore and the division is therefore complete. Flooring is done by leaving the remainder, 1, if it has it.
	@SUBTRACTION
	D;JEQ
	D-1;JEQ
	//Sum-=2
	@CONST2
	D=D-M
	//Count++;
	@COUNT
	M=M+1
	@DIVLOOP
	0;JMP

(SUBTRACTION)
	//Get pop(n-2) value
	@POP2
	D=M
	//Count-=pop(n-2)
	@COUNT
	M=M-D
	D=M

//UPDATE our values
	@R1
	M=D
	@POP1
	D=M
	@POP2
	M=D
	@R1
	D=M
	@POP1
	M=D
	@LOOP
	0;JMP

(PREEND) //If n<2, make pop(n) = n
	@R0
	D=M
	@R1
	M=D
	@END
	0;JMP

(END)
	@END
	0;JMP