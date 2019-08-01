import static groovy.io.FileType.FILES

@NonCPS

def inputParamsString(dir) {
    println pwd()
    def list = [] as java.lang.Object
    dir.eachFileRecurse(FILES) {
        if(it.name.endsWith('.js')) {
            list << it.getName()
        }
    }
    list.join("\n")
}

return this