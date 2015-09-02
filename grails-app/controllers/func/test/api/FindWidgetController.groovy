package func.test.api

import func.test.Widget
import grails.converters.JSON

class FindWidgetController {

    static allowedMethods = [findWidgets: "POST"]

    def findWidget(FindWidgetCommand command) {
        if (Widget.count != 1) {
            // There should be 1 widget in the database, if not something is wrong.
            render """{"msg":"No Widgets!!!"}"""
            return
        }
        Widget widget = Widget.read(command.widgetId)

        if (widget) {
            render """{"widgetId":${widget.id},"name":"${widget.name}"}"""
        } else {
            render """{}"""
        }
    }
}

