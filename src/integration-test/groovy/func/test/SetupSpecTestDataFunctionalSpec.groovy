package func.test

import grails.plugins.rest.client.RestBuilder
import grails.test.mixin.Mock
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
class SetupSpecTestDataFunctionalSpec extends GebSpec {

    // Gives Exception
    //    java.lang.IllegalStateException: Method on class [func.test.Widget] was used outside of a Grails application. If running in the context of a test using the mocking API or bootstrap Grails correctly.
    //      at org.grails.datastore.gorm.GormEntity$Trait$Helper.currentGormInstanceApi(GormEntity.groovy:52)
    //      at org.grails.datastore.gorm.GormEntity$Trait$Helper.save(GormEntity.groovy:165)
    //      at func.test.SetupSpecTestDataFunctionalSpec.setupSpec(SetupSpecTestDataFunctionalSpec.groovy:21)
    // Grails should be bootstrapped.
    def setupSpec() {
        Widget widget = new Widget(name: 'Test')
        widget.save(failOnError: true)
    }

    def setup() {
    }

    def cleanup() {
    }

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
