package func.test.api

import grails.validation.Validateable

class FindWidgetCommand implements Validateable {
    Long widgetId

    static constraints = {
        widgetId blank: false, nullable: false
    }
}
