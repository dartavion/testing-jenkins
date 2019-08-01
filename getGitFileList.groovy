import static groovy.io.FileType.FILES

@NonCPS

def inputParamsString(dir) {
    def list = [] as java.lang.Object
    dir.eachFile(FILES) {
        if(it.name.endsWith('.js')) {
            list << it.getName()
        }
    }
    list.join("\n")
}

return this