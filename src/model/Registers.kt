package model

class Register(val registerName: String,
               val registerValue: Int,
               var memoryAddress: Int)

val acceptedRegisters = arrayListOf(Register("r0", 0, 0),
    Register("r1", 1, 0),
    Register("r2", 2, 0),
    Register("r3", 3, 0),
    Register("r4", 4, 0),
    Register("r5", 5, 0),
    Register("r6", 6, 0),
    Register("r7", 7, 0),
    Register("r8", 8, 0),
    Register("r9", 9, 0),
    Register("r10", 10, 0),
    Register("r11", 11, 0),
    Register("r12", 12, 0),
    Register("r13", 13, 0),
    Register("r14", 14, 0),
    Register("r15", 15, 0),
    Register("r16", 16, 0),
    Register("r17", 17, 0),
    Register("r18", 18, 0),
    Register("r19", 19, 0),
    Register("r20", 20, 0),
    Register("r21", 21, 0),
    Register("r22", 22, 0),
    Register("r23", 23, 0),
    Register("r24", 24, 0),
    Register("r25", 25, 0),
    Register("r26", 26, 0),
    Register("r27", 27, 0),
    Register("r28", 28, 0),
    Register("r29", 29, 0),
    Register("r30", 30, 0),
    Register("r31", 31, 0)
)