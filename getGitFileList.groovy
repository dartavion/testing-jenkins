import static groovy.io.FileType.FILES

@NonCPS
def inputParamsString(dir) {
    def list = []
    dir.eachFileRecurse(FILES) {
        if(it.name.endsWith('.js')) {
            list << it.getName()
        }
    }
    list.join("\n")
}

return this