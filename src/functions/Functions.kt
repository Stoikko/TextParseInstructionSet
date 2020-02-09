package functions

import memAddress
import memory
import model.MemoryObject
import model.Register
import model.acceptedRegisters
import registers

fun getRegister(registerName: String): Register? {
    return registers.find { it.registerName == registerName }
}

fun getMemoryObject(address: Int): MemoryObject? {
    return memory.find { it.memoryAddress == address }
}

fun memToRegister(register: String, dataName: String){
    val objectInMemory = memory.find { it.key == dataName }
    registers.find { it.registerName == register}!!.memoryAddress = objectInMemory!!.memoryAddress
    memAddress++
}

fun stringToMem(register: String, key: String, string: String){
    memory.add(MemoryObject(key, string, null, memAddress))
    val registerVal = registers.find { it.registerName == register }
    registers.find { it.registerName == registerVal!!.registerName }!!.memoryAddress = memAddress
    memAddress++
}

fun saveStringToMem(string: String): Int {
    memory.add(MemoryObject("autoSaved", string, null, memAddress))
    memAddress++
    return memAddress - 1
}

fun combineStrings(registerDestinationName: String, leftRegisterName: String, rightRegisterName: String){
    val leftRegister = getRegister(leftRegisterName)
    val rightRegister = getRegister(rightRegisterName)
    val leftRegMem = getMemoryObject(leftRegister!!.memoryAddress)
    val rightRegMem = getMemoryObject(rightRegister!!.memoryAddress)
    val combinedString = leftRegMem!!.stringContents + rightRegMem!!.stringContents

    memory.add(MemoryObject(registerDestinationName, combinedString, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun combineWithSpace(registerDestinationName: String, leftRegisterName: String, rightRegisterName: String){
    val leftRegister = getRegister(leftRegisterName)
    val rightRegister = getRegister(rightRegisterName)
    val leftRegMem = getMemoryObject(leftRegister!!.memoryAddress)
    val rightRegMem = getMemoryObject(rightRegister!!.memoryAddress)
    val combinedString = leftRegMem!!.stringContents + " " + rightRegMem!!.stringContents

    memory.add(MemoryObject(registerDestinationName, combinedString, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun combineAppend(registerDestinationName: String, leftRegisterName: String, rightRegisterName: String){
    val leftRegister = getRegister(leftRegisterName)
    val rightRegister = getRegister(rightRegisterName)
    val registerDest = getRegister(registerDestinationName)
    val leftRegMem = getMemoryObject(leftRegister!!.memoryAddress)
    val rightRegMem = getMemoryObject(rightRegister!!.memoryAddress)
    val registerDestMem = getMemoryObject(registerDest!!.memoryAddress)
    val combinedString = registerDestMem!!.stringContents + leftRegMem!!.stringContents + rightRegMem!!.stringContents

    memory.add(MemoryObject(registerDestinationName, combinedString, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun combineAppendWithSpace(registerDestinationName: String, leftRegisterName: String, rightRegisterName: String){
    val leftRegister = getRegister(leftRegisterName)
    val rightRegister = getRegister(rightRegisterName)
    val registerDest = getRegister(registerDestinationName)
    val leftRegMem = getMemoryObject(leftRegister!!.memoryAddress)
    val rightRegMem = getMemoryObject(rightRegister!!.memoryAddress)
    val registerDestMem = getMemoryObject(registerDest!!.memoryAddress)
    val combinedString = registerDestMem!!.stringContents + " " + leftRegMem!!.stringContents + " " + rightRegMem!!.stringContents

    memory.add(MemoryObject(registerDestinationName, combinedString, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun rightCombineAppend(registerDestinationName: String, leftRegisterName: String, rightRegisterName: String){
    val leftRegister = getRegister(leftRegisterName)
    val rightRegister = getRegister(rightRegisterName)
    val registerDest = getRegister(registerDestinationName)
    val leftRegMem = getMemoryObject(leftRegister!!.memoryAddress)
    val rightRegMem = getMemoryObject(rightRegister!!.memoryAddress)
    val registerDestMem = getMemoryObject(registerDest!!.memoryAddress)
    val combinedString = leftRegMem!!.stringContents + rightRegMem!!.stringContents + registerDestMem!!.stringContents

    memory.add(MemoryObject(registerDestinationName, combinedString, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun rightCombineAppendWithSpace(registerDestinationName: String, leftRegisterName: String, rightRegisterName: String){
    val leftRegister = getRegister(leftRegisterName)
    val rightRegister = getRegister(rightRegisterName)
    val registerDest = getRegister(registerDestinationName)
    val leftRegMem = getMemoryObject(leftRegister!!.memoryAddress)
    val rightRegMem = getMemoryObject(rightRegister!!.memoryAddress)
    val registerDestMem = getMemoryObject(registerDest!!.memoryAddress)
    val combinedString = leftRegMem!!.stringContents + " " + rightRegMem!!.stringContents + " " + registerDestMem!!.stringContents

    memory.add(MemoryObject(registerDestinationName, combinedString, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun firstWord(registerDestinationName: String, registerSourceName: String){
    val registerSource = getRegister(registerSourceName)
    val memoryObject = getMemoryObject(registerSource!!.memoryAddress)
    val firstWord = memoryObject!!.stringContents!!.split(" ")[0]

    memory.add(MemoryObject(registerDestinationName, firstWord, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun firstWordAppend(registerDestinationName: String, registerSourceName: String){
    val registerSource = getRegister(registerSourceName)
    val memoryObject = getMemoryObject(registerSource!!.memoryAddress)
    val firstWord = memoryObject!!.stringContents!!.split(" ")[0]

    val registerDest = getRegister(registerDestinationName)
    val memoryDest = getMemoryObject(registerDest!!.memoryAddress)

    val combinedString = memoryDest!!.stringContents + firstWord

    memory.add(MemoryObject(registerDestinationName, combinedString, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun firstWordRightAppend(registerDestinationName: String, registerSourceName: String){
    val registerSource = getRegister(registerSourceName)
    val memoryObject = getMemoryObject(registerSource!!.memoryAddress)
    val firstWord = memoryObject!!.stringContents!!.split(" ")[0]

    val registerDest = getRegister(registerDestinationName)
    val memoryDest = getMemoryObject(registerDest!!.memoryAddress)

    val combinedString =  firstWord + memoryDest!!.stringContents

    memory.add(MemoryObject(registerDestinationName, combinedString, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun firstWordDelim(registerDestinationName: String, registerSourceName: String, deliminator: String){
    val registerSource = getRegister(registerSourceName)
    val memoryObject = getMemoryObject(registerSource!!.memoryAddress)
    val firstWord = memoryObject!!.stringContents!!.split(deliminator)[0]

    memory.add(MemoryObject(registerDestinationName, firstWord, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun firstWordDelimAppend(registerDestinationName: String, registerSourceName: String, deliminator: String){
    val registerSource = getRegister(registerSourceName)
    val memoryObject = getMemoryObject(registerSource!!.memoryAddress)
    val firstWord = memoryObject!!.stringContents!!.split(deliminator)[0]

    val registerDest = getRegister(registerDestinationName)
    val memoryDest = getMemoryObject(registerDest!!.memoryAddress)

    val combinedString = memoryDest!!.stringContents + firstWord

    memory.add(MemoryObject(registerDestinationName, combinedString, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun firstWordDelimRightAppend(registerDestinationName: String, registerSourceName: String, deliminator: String){
    val registerSource = getRegister(registerSourceName)
    val memoryObject = getMemoryObject(registerSource!!.memoryAddress)
    val firstWord = memoryObject!!.stringContents!!.split(deliminator)[0]

    val registerDest = getRegister(registerDestinationName)
    val memoryDest = getMemoryObject(registerDest!!.memoryAddress)

    val combinedString =  firstWord + memoryDest!!.stringContents

    memory.add(MemoryObject(registerDestinationName, combinedString, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun numOccurrences(registerDestinationName: String, registerSourceName: String, deliminator: String){
    val registerSource = getRegister(registerSourceName)
    val memoryObjectText = getMemoryObject(registerSource!!.memoryAddress)!!.stringContents
    val numOccurences = memoryObjectText!!.split(deliminator).size - 1

    memory.add(MemoryObject(registerDestinationName, null, numOccurences, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress++
}

fun numOccurrencesString(registerDestinationName: String, registerSourceName: String, registerString: String){
    val registerSource = getRegister(registerSourceName)
    val memoryObjectText = getMemoryObject(registerSource!!.memoryAddress)!!.stringContents

    val registerWithStringToCheckFor = getRegister(registerString)
    val memObjectWIthStringToCheckFor = getMemoryObject(registerWithStringToCheckFor!!.memoryAddress)
    val deliminator = memObjectWIthStringToCheckFor!!.stringContents
    val numOccurences = memoryObjectText!!.split(deliminator!!).size - 1

    memory.add(MemoryObject(registerDestinationName, null, numOccurences, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress++
}

fun numChar(registerDestinationName: String, registerSourceName: String){
    val registerSource = getRegister(registerSourceName)
    val memoryObjectSourceText = getMemoryObject(registerSource!!.memoryAddress)!!.stringContents
    val totalChars = memoryObjectSourceText!!.toList().size

    memory.add(MemoryObject(registerDestinationName, null, totalChars, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress++
}

fun reverseString(registerDestinationName: String, registerSourceName: String){
    val registerSource = getRegister(registerSourceName)
    val memoryObjectSourceText = getMemoryObject(registerSource!!.memoryAddress)!!.stringContents
    val stringReversed = memoryObjectSourceText!!.reversed()

    memory.add(MemoryObject(registerDestinationName, stringReversed, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress++
}

fun storeNum(registerDestinationName: String, numberToStore: String){
    val number = numberToStore.toInt()

    memory.add(MemoryObject(registerDestinationName, null, number, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress++
}

fun stringToNumber(registerDestinationName: String, registerSourceName: String){
    val registerSource = getRegister(registerSourceName)
    val memoryObjectSourceText = getMemoryObject(registerSource!!.memoryAddress)!!.stringContents
    val number = memoryObjectSourceText!!.toInt()

    memory.add(MemoryObject(registerDestinationName, null, number, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress++
}

fun addNum(registerDestinationName: String, leftRegisterName: String, rightRegisterName: String){
    val leftRegister = getRegister(leftRegisterName)
    val rightRegister = getRegister(rightRegisterName)

    val leftRegMem = getMemoryObject(leftRegister!!.memoryAddress)
    val rightRegMem = getMemoryObject(rightRegister!!.memoryAddress)

    val numbersAdded = leftRegMem!!.numberContents!! + rightRegMem!!.numberContents!!

    memory.add(MemoryObject(registerDestinationName, null, numbersAdded, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun subNum(registerDestinationName: String, leftRegisterName: String, rightRegisterName: String){
    val leftRegister = getRegister(leftRegisterName)
    val rightRegister = getRegister(rightRegisterName)

    val leftRegMem = getMemoryObject(leftRegister!!.memoryAddress)
    val rightRegMem = getMemoryObject(rightRegister!!.memoryAddress)

    val numbersSubtracted = leftRegMem!!.numberContents!! - rightRegMem!!.numberContents!!

    memory.add(MemoryObject(registerDestinationName, null, numbersSubtracted, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun divNum(registerDestinationName: String, leftRegisterName: String, rightRegisterName: String){
    val leftRegister = getRegister(leftRegisterName)
    val rightRegister = getRegister(rightRegisterName)

    val leftRegMem = getMemoryObject(leftRegister!!.memoryAddress)
    val rightRegMem = getMemoryObject(rightRegister!!.memoryAddress)

    val numbersDivided = leftRegMem!!.numberContents!! / rightRegMem!!.numberContents!!

    memory.add(MemoryObject(registerDestinationName, null, numbersDivided, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun multNum(registerDestinationName: String, leftRegisterName: String, rightRegisterName: String){
    val leftRegister = getRegister(leftRegisterName)
    val rightRegister = getRegister(rightRegisterName)

    val leftRegMem = getMemoryObject(leftRegister!!.memoryAddress)
    val rightRegMem = getMemoryObject(rightRegister!!.memoryAddress)

    val numbersMultiplied = leftRegMem!!.numberContents!! * rightRegMem!!.numberContents!!

    memory.add(MemoryObject(registerDestinationName, null, numbersMultiplied, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun findBetween(registerDestinationName: String, leftRegisterName: String, rightRegisterName: String, sourceRegisterName: String){
    val leftRegister = getRegister(leftRegisterName)
    val rightRegister = getRegister(rightRegisterName)
    val sourceRegister = getRegister(sourceRegisterName)

    val leftRegMem = getMemoryObject(leftRegister!!.memoryAddress)
    val rightRegMem = getMemoryObject(rightRegister!!.memoryAddress)
    val sourceRegMem = getMemoryObject(sourceRegister!!.memoryAddress)

    val contents = sourceRegMem!!.stringContents
    val clippedString = contents!!.substring(contents.indexOf(leftRegMem!!.stringContents!!) + leftRegMem.stringContents!!.length, contents.indexOf(rightRegMem!!.stringContents!!))

    memory.add(MemoryObject(registerDestinationName, clippedString, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun numWords(registerDestinationName: String, registerSourceName: String){
    val registerSource = getRegister(registerSourceName)
    val registerSourceText = getMemoryObject(registerSource!!.memoryAddress)!!.stringContents

    val numberOfWords = registerSourceText!!.split(" ").size

    memory.add(MemoryObject(registerDestinationName, null, numberOfWords, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++

}

fun numWordsDelim(registerDestinationName: String, registerSourceName: String, deliminator: String){
    val registerSource = getRegister(registerSourceName)
    val registerSourceText = getMemoryObject(registerSource!!.memoryAddress)!!.stringContents

    val numberOfWords = registerSourceText!!.split(deliminator).size

    memory.add(MemoryObject(registerDestinationName, null, numberOfWords, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++

}

fun clearMemAddress(address: String){
    val addressInt = address.toInt()
    try{
        registers.find { it.memoryAddress == addressInt }!!.memoryAddress = 0
    } catch (npe: KotlinNullPointerException){
        println("no register assigned to memory address $address")
    }

    memory.removeIf { it.memoryAddress == addressInt }
}

fun clearMemKey(key: String){
    memory.forEach { memObj ->
        if(memObj.key == key){
            try{
                registers.find { it.memoryAddress == memObj.memoryAddress }!!.memoryAddress = 0
            } catch (npe: KotlinNullPointerException){}
        }
    }
    memory.removeIf { it.key == key }
    memAddress--
}

fun clearMemRange(start: String, end: String){
    val startInt = start.toInt()
    val endInt = end.toInt()

    var counter = 0
    memory.forEach { memObj ->
        if(memObj.memoryAddress in startInt..endInt){
            counter++
            try{
                registers.find { it.memoryAddress == memObj.memoryAddress }!!.memoryAddress = 0
            } catch (npe: KotlinNullPointerException){}
        }
    }
    memory.removeIf { it.memoryAddress in startInt..endInt }
    memAddress -= counter
}

fun clearMem(){
    registers.clear()
    registers.addAll(acceptedRegisters)
    memory.clear()
    memAddress = 1
}

fun clearReg(registerDestinationName: String){
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = 0
}

fun clearAllReg(){
    registers.clear()
    registers.addAll(acceptedRegisters)
}

fun cutString(registerDestinationName: String, registerSourceName: String, index: String){
    val indexInt = index.toInt()
    val registerSource = getRegister(registerSourceName)
    val registerSourceText = getMemoryObject(registerSource!!.memoryAddress)!!.stringContents

    val subStr = registerSourceText!!.substring(0, indexInt)
    memory.add(MemoryObject(registerDestinationName, subStr, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun cutStringRight(registerDestinationName: String, registerSourceName: String, index: String){
    val indexInt = index.toInt()
    val registerSource = getRegister(registerSourceName)
    val registerSourceText = getMemoryObject(registerSource!!.memoryAddress)!!.stringContents

    val subStr = registerSourceText!!.substring(indexInt, registerSourceText.length)
    memory.add(MemoryObject(registerDestinationName, subStr, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun cutStringOnReg(registerDestinationName: String, registerSourceName: String, registerIndexName: String){
    val registerIndex = getRegister(registerIndexName)
    val index = getMemoryObject(registerIndex!!.memoryAddress)!!.numberContents

    val registerSource = getRegister(registerSourceName)
    val registerSourceText = getMemoryObject(registerSource!!.memoryAddress)!!.stringContents

    val subStr = registerSourceText!!.substring(0, index!!)
    memory.add(MemoryObject(registerDestinationName, subStr, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun cutStringOnRegRight(registerDestinationName: String, registerSourceName: String, registerIndexName: String){
    val registerIndex = getRegister(registerIndexName)
    val index = getMemoryObject(registerIndex!!.memoryAddress)!!.numberContents

    val registerSource = getRegister(registerSourceName)
    val registerSourceText = getMemoryObject(registerSource!!.memoryAddress)!!.stringContents

    val subStr = registerSourceText!!.substring(index!!, registerSourceText.length)
    memory.add(MemoryObject(registerDestinationName, subStr, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun addDirect(registerDestinationName: String, leftRegisterName: String, number: String){
    val leftRegister = getRegister(leftRegisterName)
    val numberInt = number.toInt()

    val leftRegMem = getMemoryObject(leftRegister!!.memoryAddress)

    val numbersAdded = leftRegMem!!.numberContents!! + numberInt

    memory.add(MemoryObject(registerDestinationName, null, numbersAdded, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun subDirect(registerDestinationName: String, leftRegisterName: String, number: String){
    val leftRegister = getRegister(leftRegisterName)
    val numberInt = number.toInt()

    val leftRegMem = getMemoryObject(leftRegister!!.memoryAddress)

    val numbersSubtracted = leftRegMem!!.numberContents!! - numberInt

    memory.add(MemoryObject(registerDestinationName, null, numbersSubtracted, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun divDirect(registerDestinationName: String, leftRegisterName: String, number: String){
    val leftRegister = getRegister(leftRegisterName)
    val numberInt = number.toInt()

    val leftRegMem = getMemoryObject(leftRegister!!.memoryAddress)

    val numbersDivided = leftRegMem!!.numberContents!! / numberInt

    memory.add(MemoryObject(registerDestinationName, null, numbersDivided, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun multDirect(registerDestinationName: String, leftRegisterName: String, number: String){
    val leftRegister = getRegister(leftRegisterName)
    val numberInt = number.toInt()

    val leftRegMem = getMemoryObject(leftRegister!!.memoryAddress)

    val numbersMultiplied = leftRegMem!!.numberContents!! * numberInt

    memory.add(MemoryObject(registerDestinationName, null, numbersMultiplied, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun copyReg(registerDestinationName: String, registerSourceName: String){
    val registerSource = getRegister(registerSourceName)
    val registerSourceMem = getMemoryObject(registerSource!!.memoryAddress)!!

    memory.add(MemoryObject(registerDestinationName, registerSourceMem.stringContents, registerSourceMem.numberContents, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun setKey(registerSourceName: String, key: String){
    val registerSource = getRegister(registerSourceName)!!
    memory.find { it.memoryAddress == registerSource.memoryAddress }!!.key = key
}

fun findString(registerDestinationName: String, string: String){
    memory.forEach { memObj ->
        if(memObj.stringContents == string){
            registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memObj.memoryAddress
            return
        }
    }
}

fun repeatString(registerDestinationName: String, times: String, string: String){
    val timesInt = times.toInt()
    val stringRepeated = string.repeat(timesInt)

    memory.add(MemoryObject(registerDestinationName, stringRepeated, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun repeatRegister(registerDestinationName: String, registerSourceName: String, times: String){
    val timesInt = times.toInt()
    val registerSource = getRegister(registerSourceName)!!
    val registerSourceText = getMemoryObject(registerSource.memoryAddress)!!.stringContents!!

    val stringRepeated = registerSourceText.repeat(timesInt)

    memory.add(MemoryObject(registerDestinationName, stringRepeated, null, memAddress))
    registers.find { it.registerName == registerDestinationName }!!.memoryAddress = memAddress
    memAddress ++
}

fun clearRepeatStrings(){
    val tempMem = memory.distinctBy { it.stringContents }
    memory.clear()
    memory.addAll(tempMem)
}

fun clearRepeatNumbers(){
    val tempMem = memory.distinctBy { it.numberContents }
    memory.clear()
    memory.addAll(tempMem)
}

fun clearRepeatStringReg(){
    val memObjsWithRegs = arrayListOf<MemoryObject>()
    registers.forEach { reg ->
        val memObj = getMemoryObject(reg.memoryAddress)
        if(memObj != null ){
            memObjsWithRegs.add(memObj)
        }
    }
    val memAddressesWithRegs = memObjsWithRegs.map { it.memoryAddress }
    val tempMem = memObjsWithRegs.distinctBy { it.stringContents }
    val tempMemAddresses = tempMem.map { it.memoryAddress }

    memAddressesWithRegs.forEach { address ->
        if(!tempMemAddresses.contains(address)){
            registers.find { it.memoryAddress == address }!!.memoryAddress = 0
        }
    }
}

fun clearRepeatNumbersReg(){
    val memObjsWithRegs = arrayListOf<MemoryObject>()
    registers.forEach { reg ->
        val memObj = getMemoryObject(reg.memoryAddress)
        if(memObj != null ){
            memObjsWithRegs.add(memObj)
        }
    }
    val memAddressesWithRegs = memObjsWithRegs.map { it.memoryAddress }
    val tempMem = memObjsWithRegs.distinctBy { it.numberContents }
    val tempMemAddresses = tempMem.map { it.memoryAddress }

    memAddressesWithRegs.forEach { address ->
        if(!tempMemAddresses.contains(address)){
            registers.find { it.memoryAddress == address }!!.memoryAddress = 0
        }
    }
}