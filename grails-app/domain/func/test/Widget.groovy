package func.test

class Widget {
    String name

    static constraints = {
        name blank: false, nullable: false
    }

    def apiModel() {
        return [
                id: id,
                name: name
        ]
    }
}
