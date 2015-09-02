package func.test

import grails.plugins.rest.client.RestBuilder
import grails.test.mixin.integration.Integration
import grails.transaction.*
import org.springframework.http.MediaType
import spock.lang.*
import geb.spock.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@Integration
@Rollback
class SetupBootStrapTestDataFunctionalSpec extends GebSpec {

    def setup() {
    }

    def cleanup() {
    }

    // Uncomment the data loading from BootStrap before running this test.

    void "test find widget"() {
        setup:
            Widget widget = Widget.findByName('Test')
            RestBuilder rest = new RestBuilder()
            def j = [
                    widgetId: widget.id
            ]
        when:
            def resp = rest.post("http://localhost:8080/api/v1/findwidget") {
                contentType MediaType.APPLICATION_JSON_VALUE
                header('Accept-Language', 'en')
                header('Accept', MediaType.APPLICATION_JSON_VALUE)
                json j
            }

        then:
            resp.statusCode.'2xxSuccessful'
            resp.json.toString() == """{"widgetId":${widget.id},"name":"${widget.name}"}""".toString()
    }
}
