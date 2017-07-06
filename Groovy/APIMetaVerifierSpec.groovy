package com.sas.commons.rest.test.samples.groovy

import com.sas.commons.rest.test.util.APIMetaVerifier

import ch.qos.logback.classic.Logger;

import spock.lang.Specification
import spock.lang.Shared
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import com.sas.commons.rest.representations.ApiMeta
import com.sas.commons.rest.representations.Link
import com.sas.commons.rest.representations.Links;
import com.sas.commons.rest.representations.ResourceProperties
import com.sas.commons.rest.representations.ApiMeta.ResourceMediaType
import org.springframework.http.HttpMethod
import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory

/**
 * Created by brprou on 8/7/2016
 */
class APIMetaVerifierSpec extends Specification {
	static final  fullUrl                     = "http://aatest.instance.openstack.sas.com:7980/capvalidation/apiMeta"
	//static final  fullUrl                     = "http://aatest.instance.openstack.sas.com:7980/dataMiningModels/apiMeta"
	//static final  fullUrl                     = "http://aatest.instance.openstack.sas.com:7980/capgateway/apiMeta"
	/**static final  fullUrl                    = "http://aatest.instance.openstack.sas.com:7980/authorization/apiMeta" DEVSTARTYEAR NULL*/
	//static final  fullUrl                     = "http://aatest.instance.openstack.sas.com:7980/capcompdef/apiMeta"
	//static final  fullUrl                     = "http://aatest.instance.openstack.sas.com:7980/cappipeline/apiMeta"
	/**static final  fullUrl                    = "http://aatest.instance.openstack.sas.com:7980/casManagement/apiMeta" INCEPTIONYEAR NULL*/
	/**static final  fullUrl                    = "http://aatest.instance.openstack.sas.com:7980/compute/apiMeta" /apiMeta/version NULL*/
	/**static final  fullUrl                    = "http://aatest.instance.openstack.sas.com:7980/casProxy/apiMeta" /apiMeta/properties/mediaTypes NULL*/
	/**static final  fullUrl                    = "http://aatest.instance.openstack.sas.com:7980/configuration/apiMeta" INCEPTIONYEAR NULL*/
	/**static final  fullUrl                    = "http://aatest.instance.openstack.sas.com:7980/credentials/apiMeta" DEVSTARTYEAR NULL*/
	//static final  fullUrl                     = "http://aatest.instance.openstack.sas.com:7980/dataSources/apiMeta"
	//static final  fullUrl                     = "http://aatest.instance.openstack.sas.com:7980/dataTables/apiMeta"
	/**static final  fullUrl                    = "http://aatest.instance.openstack.sas.com:7980/files/apiMeta" DEVSTARTYEAR NULL*/
	/**static final  fullUrl                    = "http://aatest.instance.openstack.sas.com:7980/folders/apiMeta" DEVSTARTYEAR NULL*/
	/**static final  fullUrl                    = "http://aatest.instance.openstack.sas.com:7980/identities/apiMeta" DEVSTARTYEAR NULL*/
	//static final  fullUrl                     = "http://aatest.instance.openstack.sas.com:7980/jobDefinitions/apiMeta"
	//static final  fullUrl                     = "http://aatest.instance.openstack.sas.com:7980/jobExecution/apiMeta"
	/**static final  fullUrl                    = "http://aatest.instance.openstack.sas.com:7980/modelRepository/apiMeta" /apiMeta/version NULL*/
	/**static final  fullUrl                    = "http://aatest.instance.openstack.sas.com:7980/results/apiMeta" NAME NULL */
	/**static final  fullUrl                    = "http://aatest.instance.openstack.sas.com:7980/types/apiMeta" DEVSTARTYEAR NULL*/
	private static final Logger logger          = (Logger) LoggerFactory.getLogger(APIMetaVerifierSpec.class)
	@Shared RestTemplate client
    @Shared HttpHeaders headers
	@Shared ApiMeta metadata;
	@Shared def doc;
	@Shared def media;
	@Shared def properties;
	@Shared def resources;
	@Shared def version;
	@Shared def versions;
	
	
	/**
	 * sets up various apiMeta fields to be passed into their respective verifier methods in APIMetaVerifier
	 */
	def setupSpec(){
		client  = new RestTemplate()
		headers = new HttpHeaders()
		headers.add("Accept",        'application/json')
		HttpEntity<String> request       = new HttpEntity<String>(headers)
		ResponseEntity<ApiMeta> APIbody = 							client.exchange(fullUrl, HttpMethod.GET, request, ApiMeta.class)
		ResponseEntity<Map<String, String>> APIdoc = 				client.exchange(fullUrl+'/doc', HttpMethod.GET, request, Map.class)
		ResponseEntity<List<ApiMeta.ResourceMediaTypes>> APImedia = client.exchange(fullUrl+'/mediaTypes', HttpMethod.GET, request, ApiMeta.ResourceMediaTypes.class)
		ResponseEntity<ResourceProperties> APIproperties = 			client.exchange(fullUrl+'/properties', HttpMethod.GET, request, ResourceProperties.class)
		ResponseEntity<Links> APIresources = 						client.exchange(fullUrl+'/resources', HttpMethod.GET, request, Links.class)
		ResponseEntity<String> APIversion = 						client.exchange(fullUrl+'/version', HttpMethod.GET, request, String.class)
		ResponseEntity<ResourceProperties> APIversions = 			client.exchange(fullUrl+'/versions', HttpMethod.GET, request, ResourceProperties.class)
		println ""
		metadata = 	APIbody.body
		doc =		APIdoc.body
		media = 	APImedia.body
		properties= APIproperties.body
		resources = APIresources.body
		version = 	APIversion.body
		versions = 	APIversions.body
		logger.setLevel(Level.DEBUG)
		} 
	
	def "apiMeta_version"() {
		when:
		int versionNum=0
		if(version.contains(".")){versionNum = Integer.parseInt(version.substring(version.indexOf("value\":\"")+8, version.indexOf(".")))}
		else{ versionNum = Integer.parseInt(version.substring(version.indexOf("value\":\"")+8, version.lastIndexOf("\"")))}
		println "VERIFYING APIMETA_VERSION:"
		println "version: " + versionNum
		println ""
		boolean test = APIMetaVerifier.verifyAPIMetaVersion(version);
		then:
		test == true
	}
	
    def "apiMeta_build"() {
        when:
		println "VERIFYING APIMETA_BUILD:"
		println "branch: " + metadata.build.branch.toLowerCase()
		if(logger.getLevel()==Level.DEBUG){
			println "buildVersion: " + metadata.build.buildVersion
			println "commitId: " + metadata.build.commitId
			println "commitTimeStamp: " + metadata.build.commitTimeStamp
			println "timeStamp: " + metadata.build.timeStamp
		}
		println ""
		boolean test = APIMetaVerifier.verifyAPIMetaBuild(metadata);
		then:
		test == true
	}

	def "apiMeta_doc"() {
		when:
		println "VERIFYING APIMETA_DOC:"
		println "links size: " + doc.links.size()
		if(logger.getLevel()==Level.DEBUG){
			int count = 1
			println "----------------------------------------------------"
			for(Link l : doc.links){
				println count + "| link method: " + l.method
				println count + "| link rel: " + l.rel
				println count + "| link href: " + l.href
				println count + "| link uri: " + l.uri
				println "----------------------------------------------------"
				count++
			}
		}
		println ""
		boolean test = APIMetaVerifier.verifyAPIMetaDoc(doc);
		then:
		test == true
	}
	
	def "apiMeta_properties"() {
		when:
		List<String> fields=new ArrayList<>();
		properties.toString().substring(1,properties.toString().lastIndexOf("}")).split(", ").each{
			if(it.contains("=")&&!it.contains(";")){fields.add(it.substring(0, it.indexOf("=")).trim())}}
		println "VERIFYING APIMETA_PROPERTIES:"
		println "properties size: "+ properties.properties.size()
		println "properties mediaTypes size: " + properties.mediaTypes.split(',').size()
		println "properties resources size: " + properties.resources.split(',').size()
		if(logger.getLevel()==Level.DEBUG){
			int count = 1
			println "----------------------------------------------------"
			fields.each{
				println count + "| "+ it + ": " + properties[it]
				println "----------------------------------------------------"
				count ++
			}
		}
		println ""
		boolean test = APIMetaVerifier.verifyAPIMetaProperties(properties);
		then:
		test == true
	}
	
	def "apiMeta_mediaTypes"() {
		when:
		println "VERIFYING APIMETA_MEDIATYPES:"
		println "mediaTypes size: " + media.mediaTypes.size()
		if(logger.getLevel()==Level.DEBUG){
			int count = 1
			println "----------------------------------------------------"
			for(ResourceMediaType r : media.mediaTypes){
				println count + "| mediaType name: " + r.name
				println count + "| mediaType version: " + r.version
				println count + "| mediaType deprecated: " + r.deprecated
				println "----------------------------------------------------"
				count++
			}
		}
		println ""
		boolean test = APIMetaVerifier.verifyAPIMetaMediaTypes(media);
		then:
		test == true
	}
	
	def "apiMeta_resources"() {
		when:
		println "VERIFYING APIMETA_RESOURCES:"
		println "resources links size: " + resources.size()
		if(logger.getLevel()==Level.DEBUG){
			int count = 1
			println "----------------------------------------------------"
			for(Link l : resources.links){
				println count + "| link method: " + l.method
				println count + "| link rel: " + l.rel
				println count + "| link href: " + l.href
				println count + "| link uri: " + l.uri
				println "----------------------------------------------------"
				count++
			}
		}
		println ""
		boolean test = APIMetaVerifier.verifyAPIMetaResources(resources);
		then:
		assert test == true
	}
	
	def "apiMeta_versions"() {
		when:
		List<String> fields=new ArrayList<>();
		versions.toString().substring(1,versions.toString().lastIndexOf("}")).split(",").each{fields.add(it.substring(0, it.indexOf("=")).trim())}
		println "VERIFYING APIMETA_VERSIONS:"
		println "versions size: " + versions.size()
		if(logger.getLevel()==Level.DEBUG){
			int count = 1
			println "----------------------------------------------------"
			fields.each{
				println count + "| field: " + it
				println count + "| version: " + versions[it]
				println "----------------------------------------------------"
				count ++
			}
		}
		boolean test = APIMetaVerifier.verifyAPIMetaVersions(versions);
		then:
		test == true
	}

}
