import java.io.FileInputStream
import java.util.*

fun groupAndNumberTextLines(roles: List<String>, textLines: List<String>): String {
    val roleMap = mutableMapOf<String, MutableList<String>>()

    for (role in roles) {
        roleMap[role] = mutableListOf()
    }

    for ((index, line) in textLines.withIndex()) {
        val (role, text) = line.split(": ", limit = 2)
        val fullRole = findMatchingRole(role, roles)
        roleMap[fullRole]?.add("${index + 1}) $text")
    }

    val result = StringBuilder()
    for (role in roles) {
        result.appendln("$role:")
        roleMap[role]?.let { result.append(it.joinToString("\n")) }
        result.appendln("\n")
    }

    return result.toString()
}

fun findMatchingRole(role: String, roles: List<String>): String {
    return roles.firstOrNull { it == role || role.startsWith("$it ") } ?: role
}

fun main() {
    val sc  = Scanner(FileInputStream("text.txt"))

    var roles = mutableListOf("")
    var textLines = mutableListOf("")

    var line = ""
    var flag = ""
    while (sc.hasNextLine()) {

        line = sc.nextLine()
        if (line == "roles:"){
            flag = "roles"
        } else if (line == "textLines:"){
            flag = "textLines"
        } else {
            if (flag == "roles"){
                roles.add(line)
            } else{
                textLines.add(line)
            }
        }
    }
    roles.removeFirst()
    textLines.removeFirst()


    val result = groupAndNumberTextLines(roles, textLines)
    println(result)
}
