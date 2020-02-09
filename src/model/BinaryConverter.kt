package model

import functions.*
import jdk.jfr.MemoryAddress
import memory
import registers

fun intToBinary(number: Int, totalBits: Int): String{
    var binaryRepresentation = Integer.toBinaryString(number)

    if(binaryRepresentation.length < totalBits){
        var i = binaryRepresentation.length
        while(i < totalBits){
            binaryRepresentation = "0$binaryRepresentation"
            i++
        }
    } else if (binaryRepresentation.length > totalBits){
        return "Number is out of range of accepted number of bits"
    }

    return binaryRepresentation
}

fun stringToCharInt(string: String): Int{
    if(string.length != 1){
        println("String too long to translate to Unicode char!: $string")
        return -1
    }
    var charCode = -1
    string.toCharArray().forEach { character ->
        charCode = character.toInt()
    }
    return charCode
}

fun getOpcodeWithDestLeftRight(functionCode: String, registerDest: String?, leftRegister: String?, rightRegister: String?): String {
    val registerDestCode = intToBinary(registers.find { it.registerName == registerDest }!!.registerValue, 5)
    val registerLeftCode = intToBinary(registers.find { it.registerName == leftRegister }!!.registerValue, 5)
    val registerRightCode = intToBinary(registers.find { it.registerName == rightRegister }!!.registerValue, 5)
    return "$functionCode | $registerDestCode | $registerLeftCode | $registerRightCode | 00000000000"
}

fun getOpcodeWithDestAndMem(functionCode: String, registerDest: String?, key: String?): String{
    val registerCode = intToBinary(registers.find { it.registerName == registerDest }!!.registerValue, 5)
    val memoryAddressCode = intToBinary(memory.find { it.key == key }!!.memoryAddress, 21)
    return "$functionCode | $registerCode | $memoryAddressCode"
}

fun getOpcodeWithDestAndSource(functionCode: String, registerDest: String?, registerSource: String?): String{
    val registerDestCode = intToBinary(registers.find { it.registerName == registerDest }!!.registerValue, 5)
    val registerSourceCode = intToBinary(registers.find { it.registerName == registerSource }!!.registerValue, 5)
    return "$functionCode | $registerDestCode | $registerSourceCode | 0000000000000000"
}

fun getOpcodeWithDestSourceAndDelim(functionCode: String, registerDest: String?, registerSource: String?, deliminator: String): String{
    val registerDestCode = intToBinary(registers.find { it.registerName == registerDest }!!.registerValue, 5)
    val registerSourceCode = intToBinary(registers.find { it.registerName == registerSource }!!.registerValue, 5)
    val delimInt = stringToCharInt(deliminator)
    val deliminatorCode = intToBinary(delimInt, 16)
    return "$functionCode | $registerDestCode | $registerSourceCode | $deliminatorCode"
}

fun getOpcodeWithDestSourceAndIndex(functionCode: String, registerDest: String?, registerSource: String?, index: String): String{
    val registerDestCode = intToBinary(registers.find { it.registerName == registerDest }!!.registerValue, 5)
    val registerSourceCode = intToBinary(registers.find { it.registerName == registerSource }!!.registerValue, 5)
    val indexInt = index.toInt()
    val indexCode = intToBinary(indexInt, 16)
    return "$functionCode | $registerDestCode | $registerSourceCode | $indexCode "
}

fun getOpcodeWithDestAndAddress(functionCode: String, registerDest: String?, memoryAddress: Int): String{
    val registerDestCode = intToBinary(registers.find { it.registerName == registerDest }!!.registerValue, 5)
    val addressCode = intToBinary(memoryAddress, 21)
    return "$functionCode | $registerDestCode | $addressCode"
}

fun getOpcode(function: String, registerDest: String?, leftRegister: String?, rightRegister: String?, registerSource: String?, key: String?, deliminator: String?): String{
    when(function){
        "memToRegister" -> {
            val functionCode = intToBinary(0, 6)
            return getOpcodeWithDestAndMem(functionCode, registerDest, key)
        }
        "stringToMem" -> {
            val functionCode = intToBinary(1, 6)
            return getOpcodeWithDestAndMem(functionCode, registerDest, key)
        }
        "combineStrings" -> {
            val functionCode = intToBinary(2, 6)
            return getOpcodeWithDestLeftRight(functionCode, registerDest, leftRegister, rightRegister)
        }
        "combineWithSpace" -> {
            val functionCode = intToBinary(3, 6)
            return getOpcodeWithDestLeftRight(functionCode, registerDest, leftRegister, rightRegister)
        }
        "combineAppend" -> {
            val functionCode = intToBinary(4, 6)
            return getOpcodeWithDestLeftRight(functionCode, registerDest, leftRegister, rightRegister)
        }
        "combineAppendWithSpace" -> {
            val functionCode = intToBinary(5, 6)
            return getOpcodeWithDestLeftRight(functionCode, registerDest, leftRegister, rightRegister)
        }
        "rightCombineAppend" -> {
            val functionCode = intToBinary(6, 6)
            return getOpcodeWithDestLeftRight(functionCode, registerDest, leftRegister, rightRegister)
        }
        "rightCombineApendWithSpace" -> {
            val functionCode = intToBinary(7, 6)
            return getOpcodeWithDestLeftRight(functionCode, registerDest, leftRegister, rightRegister)
        }
        "firstWord" -> {
            val functionCode = intToBinary(8, 6)
            return getOpcodeWithDestAndSource(functionCode, registerDest, registerSource)
        }
        "firstWordAppend" -> {
            val functionCode = intToBinary(9, 6)
            return getOpcodeWithDestAndSource(functionCode, registerDest, registerSource)
        }
        "firstWordRightAppend" -> {
            val functionCode = intToBinary(10, 6)
            return getOpcodeWithDestAndSource(functionCode, registerDest, registerSource)
        }
        "firstWordDelim" -> {
            val functionCode = intToBinary(11, 6)
            return getOpcodeWithDestSourceAndDelim(functionCode, registerDest, registerSource, deliminator!!)
        }
        "firstWordDelimAppend" -> {
            val functionCode = intToBinary(12, 6)
            return getOpcodeWithDestSourceAndDelim(functionCode, registerDest, registerSource, deliminator!!)
        }
        "firstWordDelimRightAppend" -> {
            val functionCode = intToBinary(13, 6)
            return getOpcodeWithDestSourceAndDelim(functionCode, registerDest, registerSource, deliminator!!)
        }
        "numOccurrences" -> {
            val functionCode = intToBinary(14, 6)
            return getOpcodeWithDestSourceAndDelim(functionCode, registerDest, registerSource, deliminator!!)
        }
        "numOccurrencesString" -> {
            val functionCode = intToBinary(15, 6)
            return getOpcodeWithDestLeftRight(functionCode, registerDest, leftRegister, rightRegister)
        }
        "numChar" -> {
            val functionCode = intToBinary(16, 6)
            return getOpcodeWithDestAndSource(functionCode, registerDest, registerSource)
        }
        "reverseString" -> {
            val functionCode = intToBinary(17, 6)
            return getOpcodeWithDestAndSource(functionCode, registerDest, registerSource)
        }
        "storeNum" -> {
            val functionCode = intToBinary(18, 6)
            val registerCode = intToBinary(registers.find { it.registerName == registerDest }!!.registerValue, 5)
            val numberCode = intToBinary(leftRegister!!.toInt(), 21)
            return "$functionCode | $registerCode | $numberCode"
        }
        "stringToNumber" -> {
            val functionCode = intToBinary(19, 6)
            return getOpcodeWithDestAndSource(functionCode, registerDest, registerSource)
        }
        "addNum" -> {
            val functionCode = intToBinary(20, 6)
            return getOpcodeWithDestLeftRight(functionCode, registerDest, leftRegister, rightRegister)
        }
        "subNum" -> {
            val functionCode = intToBinary(21, 6)
            return getOpcodeWithDestLeftRight(functionCode, registerDest, leftRegister, rightRegister)
        }
        "divNum" -> {
            val functionCode = intToBinary(22, 6)
            return getOpcodeWithDestLeftRight(functionCode, registerDest, leftRegister, rightRegister)
        }
        "multNum" -> {
            val functionCode = intToBinary(23, 6)
            return getOpcodeWithDestLeftRight(functionCode, registerDest, leftRegister, rightRegister)
        }
        "findBetween" -> {
            val functionCode = intToBinary(24, 6)
            val registerDestCode = intToBinary(registers.find { it.registerName == registerDest }!!.registerValue, 5)
            val registerLeftCode = intToBinary(registers.find { it.registerName == leftRegister }!!.registerValue, 5)
            val registerRightCode = intToBinary(registers.find { it.registerName == rightRegister }!!.registerValue, 5)
            val registerSourceCode = intToBinary(registers.find { it.registerName == registerSource }!!.registerValue, 5)
            return "$functionCode | $registerDestCode | $registerLeftCode | $registerRightCode | $registerSourceCode | 000000"
        }
        "numWords" -> {
            val functionCode = intToBinary(25, 6)
            return getOpcodeWithDestAndSource(functionCode, registerDest, registerSource)
        }
        "numWordsDelim" -> {
            val functionCode = intToBinary(26, 6)
            return getOpcodeWithDestSourceAndDelim(functionCode, registerDest, registerSource, deliminator!!)
        }
        "clearMemAddress" -> {
            val functionCode = intToBinary(27, 6)
            val address = registerDest!!.toInt()
            val addressCode = intToBinary(address, 26)
            return "$functionCode | $addressCode"
        }
        "clearMemKey" -> {
            val functionCode = intToBinary(28, 6)
            return "$functionCode | 00000000000000000000000000"
        }
        "clearMemRange" -> {
            val functionCode = intToBinary(29, 6)
            val startAddress = leftRegister!!.toInt()
            val endAddress = rightRegister!!.toInt()

            val startAddressCode = intToBinary(startAddress, 13)
            val endAddressCode = intToBinary(endAddress, 13)

            return "$functionCode | $startAddressCode | $endAddressCode"
        }
        "clearMem" -> {
            val functionCode = intToBinary(30, 6)
            return "$functionCode | 00000000000000000000000000"
        }
        "clearReg" -> {
            val functionCode = intToBinary(31, 6)
            val registerDestCode = intToBinary(registers.find { it.registerName == registerDest }!!.registerValue, 5)
            return "$functionCode | $registerDestCode | 000000000000000000000"

        }
        "clearAllReg" -> {
            val functionCode = intToBinary(32, 6)
            return "$functionCode | 00000000000000000000000000"
        }
        "cutString" -> {
            val functionCode = intToBinary(33, 6)
            return getOpcodeWithDestSourceAndIndex(functionCode, registerDest, registerSource, key!!)
        }
        "cutStringRight" -> {
            val functionCode = intToBinary(34, 6)
            return getOpcodeWithDestSourceAndIndex(functionCode, registerDest, registerSource, key!!)
        }
        "cutStringOnReg" -> {
            val functionCode = intToBinary(35, 6)
            return getOpcodeWithDestLeftRight(functionCode, registerDest, leftRegister, rightRegister)
        }
        "cutStringOnRegRight" -> {
            val functionCode = intToBinary(36, 6)
            return getOpcodeWithDestLeftRight(functionCode, registerDest, leftRegister, rightRegister)
        }
        "addDirect" -> {
            val functionCode = intToBinary(37, 6)
            return getOpcodeWithDestSourceAndIndex(functionCode, registerDest, leftRegister, key!!)
        }
        "subDirect" -> {
            val functionCode = intToBinary(38, 6)
            return getOpcodeWithDestSourceAndIndex(functionCode, registerDest, leftRegister, key!!)
        }
        "divDirect" -> {
            val functionCode = intToBinary(39, 6)
            return getOpcodeWithDestSourceAndIndex(functionCode, registerDest, leftRegister, key!!)
        }
        "multDirect" -> {
            val functionCode = intToBinary(40, 6)
            return getOpcodeWithDestSourceAndIndex(functionCode, registerDest, leftRegister, key!!)
        }
        "copyReg" -> {
            val functionCode = intToBinary(41, 6)
            return getOpcodeWithDestAndSource(functionCode, registerDest, registerSource)
        }
        "setKey" -> {
            val functionCode = intToBinary(42, 6)
            val addressInt = key!!.toInt()
            return getOpcodeWithDestAndAddress(functionCode, registerDest, addressInt)
        }
        "findString" -> {
            val functionCode = intToBinary(43, 6)
            val addressInt = key!!.toInt()
            return getOpcodeWithDestAndAddress(functionCode, registerDest, addressInt)
        }
        "repeatString" -> {
            val functionCode = intToBinary(44, 6)
            val registerDestCode = intToBinary(registers.find { it.registerName == registerDest }!!.registerValue, 5)
            val numberCode = intToBinary(leftRegister!!.toInt(), 8)
            val addressCode = intToBinary(key!!.toInt(), 13)
            return "$functionCode | $registerDestCode | $numberCode | $addressCode"
        }
        "repeatReg" -> {
            val functionCode = intToBinary(45, 6)
            return getOpcodeWithDestSourceAndIndex(functionCode, registerDest, registerSource, key!!)
        }
        "clearRepeatStrings" -> {
            val functionCode = intToBinary(46, 6)
            return "$functionCode | 00000000000000000000000000"
        }
        "clearRepeatNumbers" -> {
            val functionCode = intToBinary(47, 6)
            return "$functionCode | 00000000000000000000000000"
        }
        "clearRepeatStringsReg" -> {
            val functionCode = intToBinary(48, 6)
            return "$functionCode | 00000000000000000000000000"
        }
        "clearRepeatNumbersReg" -> {
            val functionCode = intToBinary(49, 6)
            return "$functionCode | 00000000000000000000000000"
        }
        "cleanMem" -> {
            val functionCode = intToBinary(50, 6)
            return "$functionCode | 00000000000000000000000000"
        }
        else -> println("Function not found: $function")
    }

    return ""
}