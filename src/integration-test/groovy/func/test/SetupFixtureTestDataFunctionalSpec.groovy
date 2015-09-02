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
class SetupFixtureTestDataFunctionalSpec extends GebSpec {

    def setup() {
        // This generates a Hibernate Exception
        //        org.hibernate.HibernateException: No Session found for current thread
        //          at org.grails.orm.hibernate.GrailsSessionContext.currentSession(GrailsSessionContext.java:117)
        //          at org.hibernate.internal.SessionFactoryImpl.getCurrentSession(SessionFactoryImpl.java:1014)
        //          at org.grails.orm.hibernate.validation.HibernateDomainClassValidator.validate(HibernateDomainClassValidator.java:64)
        //          at org.grails.orm.hibernate.AbstractHibernateGormInstanceApi.save(AbstractHibernateGormInstanceApi.groovy:140)
        //          at org.grails.datastore.gorm.GormEntity$Trait$Helper.save(GormEntity.groovy:165)
        //          at func.test.SetupFixtureTestDataFunctionalSpec.setup(SetupFixtureTestDataFunctionalSpec.groovy:19)
        Widget widget = new Widget(name: 'Test')
        widget.save(failOnError: true)
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