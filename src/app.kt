import functions.*
import model.*
import java.io.File

val memory = arrayListOf<MemoryObject>()
val registers = acceptedRegisters
var memAddress = 1

fun main(args: Array<String>) {
    readFile(args[0])
}

fun readFile(path: String){
    File(path).forEachLine { parseLine(it) }
}

var dataSection = false
fun parseLine(line: String){
    //use slashes for comments
    if(line.startsWith("//")){
        return
    }

    //check if we should store data next or parse functions
    if(line == "data:"){
        dataSection = true
        return
    } else if(line == "actions:") {
        dataSection = false
        return
    }

    if(dataSection){
        if(line.isBlank()){
            return
        }
        val dataName = line.subSequence(0, line.indexOf(" = ")).toString().trim()
        val dataContent = line.subSequence(line.indexOf("\"") + 1, line.lastIndexOf("\"")).toString()
        memory.add(MemoryObject(dataName, dataContent, null, memAddress))
        memAddress++
    } else {
        if(line.contains("(")){
            val function = line.subSequence(0, line.indexOf("(")).toString().trim()
            val arguments = line.subSequence(line.indexOf("(") + 1, line.indexOf(")")).split(", ")

            //trim leading/tailing quotes
            val argumentsEdited  = arrayListOf<String>()
            arguments.forEach { arg ->
                if(arg.length > 1 &&arg[0].toString() == "\"" && arg[arg.length - 1].toString() == "\""){
                    val newArg = arg.substring(1, arg.length - 1)
                    argumentsEdited.add(newArg)
                } else {
                    argumentsEdited.add(arg)
                }
            }
            evaluateFunction(function, argumentsEdited)
        }
    }


}

fun evaluateFunction(function: String, arguments: List<String>){
    when(function){
        "memToRegister" -> {
            println("Performing memToRegister(" + arguments[0] + ", " + arguments[1] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null, null, arguments[1], null))
            memToRegister(arguments[0], arguments[1])
            printRegsAndMemContents()
        }
        "stringToMem" -> {
            println("Performing stringToMem(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            stringToMem(arguments[0], arguments[1], arguments[2])
            println("opcode: " + getOpcode(function, arguments[0], null, null, null, arguments[1], null))
            printRegsAndMemContents()
        }
        "combineStrings" -> {
            println("Performing combineStrings(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], arguments[2], null, arguments[1], null))
            combineStrings(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "combineWithSpace" -> {
            println("Performing combineWithSpace(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], arguments[2], null, arguments[1], null))
            combineWithSpace(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "combineAppend" -> {
            println("Performing combineAppend(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], arguments[2], null, arguments[1], null))
            combineAppend(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "combineAppendWithSpace" -> {
            println("Performing combineAppendWithSpace(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], arguments[2], null, arguments[1], null))
            combineAppendWithSpace(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "rightCombineAppend" -> {
            println("Performing rightCombineAppend(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], arguments[2], null, arguments[1], null))
            rightCombineAppend(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "rightCombineAppendWithSpace" -> {
            println("Performing rightCombineAppendWithSpace(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], arguments[2], null, arguments[1], null))
            rightCombineAppendWithSpace(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "firstWord" -> {
            println("Performing firstWord(" + arguments[0] + ", " + arguments[1] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null, arguments[1], null, null))
            firstWord(arguments[0], arguments[1])
            printRegsAndMemContents()
        }
        "firstWordAppend" -> {
            println("Performing firstWordAppend(" + arguments[0] + ", " + arguments[1] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null,  arguments[1], null, null))
            firstWordAppend(arguments[0], arguments[1])
            printRegsAndMemContents()
        }
        "firstWordRightAppend" -> {
            println("Performing firstWordRightAppend(" + arguments[0] + ", " + arguments[1] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null,  arguments[1], null, null))
            firstWordRightAppend(arguments[0], arguments[1])
            printRegsAndMemContents()
        }
        "firstWordDelim" -> {
            println("Performing firstWordDelim(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null,  arguments[1], null, arguments[2]))
            firstWordDelim(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "firstWordDelimAppend" -> {
            println("Performing firstWordDelimAppend(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null,  arguments[1], null, arguments[2]))
            firstWordDelimAppend(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "firstWordDelimRightAppend" -> {
            println("Performing firstWordDelimRightAppend(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null,  arguments[1], null, arguments[2]))
            firstWordDelimRightAppend(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "numOccurrences" -> {
            println("Performing numOccurrences(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null,  arguments[1], null, arguments[2]))
            numOccurrences(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "numOccurrencesString" -> {
            println("Performing numOccurrencesString(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], arguments[2],  null, null, arguments[2]))
            numOccurrencesString(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "numChar" -> {
            println("Performing numChar(" + arguments[0] + ", " + arguments[1] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null, arguments[1], null, null))
            numChar(arguments[0], arguments[1])
            printRegsAndMemContents()
        }
        "reverseString" -> {
            println("Performing reverseString(" + arguments[0] + ", " + arguments[1] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null, arguments[1], null, null))
            reverseString(arguments[0], arguments[1])
            printRegsAndMemContents()
        }
        "storeNum" -> {
            println("Performing storeNum(" + arguments[0] + ", " + arguments[1] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], null, null, null, null))
            storeNum(arguments[0], arguments[1])
            printRegsAndMemContents()
        }
        "stringToNumber" ->  {
            println("Performing stringToNumber(" + arguments[0] + ", " + arguments[1] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null, arguments[1], null, null))
            stringToNumber(arguments[0], arguments[1])
            printRegsAndMemContents()
        }
        "addNum" -> {
            println("Performing addNum(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], arguments[2], null, null, null))
            addNum(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "subNum" -> {
            println("Performing subNum(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], arguments[2], null, null, null))
            subNum(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "divNum" -> {
            println("Performing divNum(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], arguments[2], null, null, null))
            divNum(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "multNum" -> {
            println("Performing multNum(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], arguments[2], null, null, null))
            multNum(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "findBetween" -> {
            println("Performing findBetween(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ", " + arguments[3] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], arguments[2], arguments[3], null, null))
            findBetween(arguments[0], arguments[1], arguments[2], arguments[3])
            printRegsAndMemContents()
        }
        "numWords" -> {
            println("Performing numWords(" + arguments[0] + ", " + arguments[1] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null, arguments[1], null, null))
            numWords(arguments[0], arguments[1])
            printRegsAndMemContents()
        }
        "numWordsDelim" -> {
            println("Performing numWordsDelim(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null,  arguments[1], null, arguments[2]))
            numWordsDelim(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "clearMemAddress" -> {
            println("Performing clearMemAddress(" + arguments[0] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null,  null, null, null))
            clearMemAddress(arguments[0])
            printRegsAndMemContents()
        }
        "clearMemKey" -> {
            println("Performing clearMemKey(" + arguments[0] + ")")
            println("opcode: " + getOpcode(function, null, null, null,  null, null, null))
            clearMemKey(arguments[0])
            printRegsAndMemContents()
        }
        "clearMemRange" -> {
            println("Performing clearMemRange(" + arguments[0] + ", " + arguments[1] + ")")
            println("opcode: " + getOpcode(function, null, arguments[0], arguments[1],  null, null, null))
            clearMemRange(arguments[0], arguments[1])
            printRegsAndMemContents()
        }
        "clearMem" -> {
            println("Performing clearMem()")
            println("opcode: " + getOpcode(function, null, null, null,  null, null, null))
            clearMem()
            printRegsAndMemContents()
        }
        "clearReg" -> {
            println("Performing clearReg(" + arguments[0] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null,  null, null, null))
            clearReg(arguments[0])
            printRegsAndMemContents()
        }
        "clearAllReg" -> {
            println("Performing clearAllReg()")
            println("opcode: " + getOpcode(function, null, null, null,  null, null, null))
            clearAllReg()
            printRegsAndMemContents()
        }
        "cutString" -> {
            println("Performing cutString(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null, arguments[1], arguments[2], null))
            cutString(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "cutStringRight" -> {
            println("Performing cutStringRight(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null, arguments[1], arguments[2], null))
            cutStringRight(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "cutStringOnReg" -> {
            println("Performing cutStringOnReg(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], arguments[2], null, null,null))
            cutStringOnReg(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "cutStringOnRegRight" -> {
            println("Performing cutStringOnRegRight(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], arguments[2], null, null,null))
            cutStringOnRegRight(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "addDirect" -> {
            println("Performing addDirect(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], null, null, arguments[2],null))
            addDirect(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "subDirect" -> {
            println("Performing subDirect(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], null, null, arguments[2],null))
            subDirect(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "divDirect" -> {
            println("Performing divDirect(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], null, null, arguments[2],null))
            divDirect(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "multDirect" -> {
            println("Performing multDirect(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], null, null, arguments[2],null))
            multDirect(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "copyReg" -> {
            println("Performing copyReg(" + arguments[0] + ", " + arguments[1] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null, arguments[1], null, null))
            copyReg(arguments[0], arguments[1])
            printRegsAndMemContents()
        }
        "setKey" -> {
            //first save string to memory
            val addressOfString = saveStringToMem(arguments[1]).toString()
            println("Performing setKey(" + arguments[0] + ", " + arguments[1] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null, null, addressOfString, null))
            setKey(arguments[0], arguments[1])
            printRegsAndMemContents()
        }
        "findString" -> {
            //first save string to memory
            val addressOfString = saveStringToMem(arguments[1]).toString()
            println("Performing findString(" + arguments[0] + ", " + arguments[1] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null, null, addressOfString, null))
            findString(arguments[0], arguments[1])
            printRegsAndMemContents()
        }
        "repeatString" -> {
            //first save string to memory
            val addressOfString = saveStringToMem(arguments[2]).toString()
            println("Performing repeatString(" + arguments[0] + ", " + arguments[1] + ")")
            println("opcode: " + getOpcode(function, arguments[0], arguments[1], null, null, addressOfString, null))
            repeatString(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "repeatRegister" -> {
            println("Performing repeatRegister(" + arguments[0] + ", " + arguments[1] + ", " + arguments[2] + ")")
            println("opcode: " + getOpcode(function, arguments[0], null, null, arguments[1], arguments[2],null))
            repeatRegister(arguments[0], arguments[1], arguments[2])
            printRegsAndMemContents()
        }
        "clearRepeatStrings" -> {
            println("Performing clearRepeatStrings()")
            println("opcode: " + getOpcode(function, null, null, null, null, null,null))
            clearRepeatStrings()
            printRegsAndMemContents()
        }
        "clearRepeatNumbers" -> {
            println("Performing clearRepeatNumbers()")
            println("opcode: " + getOpcode(function, null, null, null, null, null,null))
            clearRepeatNumbers()
            printRegsAndMemContents()
        }
        "clearRepeatStringReg" -> {
            println("Performing clearRepeatStringReg()")
            println("opcode: " + getOpcode(function, null, null, null, null, null,null))
            clearRepeatStringReg()
            printRegsAndMemContents()
        }
        "clearRepeatNumbersReg" -> {
            println("Performing clearRepeatNumbersReg()")
            println("opcode: " + getOpcode(function, null, null, null, null, null,null))
            clearRepeatNumbersReg()
            printRegsAndMemContents()
        }
        else -> println("Function not found: $function")
    }
}

fun printRegsAndMemContents(){
    println("instruction executed")
    println("Registers: ")
    printRegisters()
    println("Current Memory Contents: ")
    printMemory()
    println("--------------------------------------------------------------")
}

fun printRegisters(){
    registers.forEach { register ->
        if(register.memoryAddress != 0){
            println("Register: " + register.registerName + " -- points to mem address: " + register.memoryAddress)
        }
    }
}

fun printMemory(){
    memory.forEach { memObj ->
        println("Key: " + memObj.key + " -- string contents: " + memObj.stringContents + " -- numeric contents: " + memObj.numberContents + " -- address: " + memObj.memoryAddress)
    }
}