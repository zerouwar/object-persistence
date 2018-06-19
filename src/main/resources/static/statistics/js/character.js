function calculate() {
    if (statistics.exclude.source === "(?:)") {
        return statistics.str.split('').length
    } else {
        return statistics.str.split('').filter(c => excluding(c)).length
    }
}

function excluding(c) {
    if (statistics.exclude) {
        let regex = statistics.exclude
        return !regex.test(c)
    }
}

function calculateCharacter() {
    let str = $("#textarea").val()
    let regex = new RegExp($("#exclude").val())

    statistics.str = str
    statistics.exclude = regex

    let number = calculate()

    $("#charNumber").val(number)
}
