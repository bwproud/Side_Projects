package com.sas.commons.rest.test.util;

import com.sas.commons.rest.representations.ApiMeta
import com.sas.commons.rest.representations.Links
import com.sas.commons.rest.representations.ResourceProperties

/**
 * Used to verify information contained in generated Api Metadata.
 * Contains methods to verify the build, doc, properties, mediaTypes,
 * resources, version, and versions.
 * @author brprou
 */
public class APIMetaVerifier{
	
	/**
	 * Method to verify common APIMeta_Build items:
	 * 1. /apiMeta/build is not null
	 * 2. branch is head
	 * 3. commitId size
	 * 4. commit time stamp
	 * 5. build time stamp
	 * @param metadata an ApiMeta object who's build is checked
	 * @return True if the build is valid, if the build is not valid, an
	 * exception would be thrown
	 */
	public static boolean verifyAPIMetaBuild(ApiMeta metadata){
		assert metadata  != null
	    assert metadata.build  != null
	    assert metadata.build.branch.toLowerCase() ==  'head'
	    assert metadata.build.buildVersion    =~ /[0-9].[0-9].[0-9]-SNAPSHOT/ || metadata.build.buildVersion  =~ /[0-9].[0-9].[0-9]{2}-SNAPSHOT/ || metadata.build.buildVersion    =~ /[0-9].[0-9].[0-9]-m.[0-9]/ || metadata.build.buildVersion    =~ /[0-9].[0-9].[0-9]{2}/ || metadata.build.buildVersion    =~ /[0-9].[0-9]{2}.[0-9]{2}-m.[0-9]{2}/ || metadata.build.buildVersion    =~ /[0-9].[0-9].[0-9]/
	    assert metadata.build.commitId.size() >= 40
	    assert metadata.build.commitTimeStamp =~ /[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}Z/
	    assert metadata.build.timeStamp       =~ /[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}Z/
	    return true
	}
	
	/**
	 * Method to verify common APIMeta_Doc items:
	 * 1. /apiMeta/doc is not null
	 * 2. doc link size is valid
	 * 3. Every doc link has a valid:
	 * 		3a. Method
	 * 		3b. Rel
	 * 		3c. Href
	 * 		3d. Uri
	 * @param doc a Map object whos links are checked
	 * @return True if the doc is valid, if the doc is not valid, an
	 * exception would be thrown
	 */
	public static boolean verifyAPIMetaDoc(Map<String, String> doc){
		assert doc != null
		assert doc.links.size =~ /[0-9]/
		for(def l : doc.links){
			assert l.method !=null
			assert l.rel !=null
			assert l.href !=null
			assert l.uri !=null
		}
		return true;
	}
	
	/**
	 * Method to verify common APIMeta_Properties items:
	 * 1. /apiMeta/properties is not null
	 * 2. properties size is valid
	 * 3. properties/mediaTypes size is valid
	 * 4. properties/resources size is valid
	 * 5. name is not null
	 * 6. serviceId is not null
	 * 7. inceptionYear is greater than 1976 
	 * 8. developmentStart year is greater than 1976 
	 * @param properties a ResourceProperties object who's properties are checked
	 * @return True if the properties are valid, if the properties are not valid, an
	 * exception would be thrown
	 */
	public static boolean verifyAPIMetaProperties(ResourceProperties properties){
		assert properties !=null
		assert properties.properties.size() 		   =~ /[0-9]/
		assert properties.mediaTypes.split(',').size() =~ /[0-9]/
		assert properties.resources.split(',').size()  =~ /[0-9]/
        assert properties.name                         != null
        assert properties.serviceId                    != null
		assert Integer.parseInt(properties.inceptionYear)				 > 1976
		assert Integer.parseInt(properties.developmentStartYear)		   > 1976
		return true;
	}
	
	/**
	 * Method to verify common APIMeta_MediaTypes items:
	 * 1. /apiMeta/mediaTypes is not null
	 * 2. mediatypes size is greater than 0
	 * 3. Every mediaType has a valid:
	 * 		3a. Name
	 * 		3b. Version
	 * 		3c. Deprecation status
	 * @param media an ApiMeta object who's mediaTypes are checked
	 * @return True if the mediaTypes are valid, if the mediaTypes are not valid, an
	 * exception would be thrown
	 */
	public static boolean verifyAPIMetaMediaTypes(ApiMeta.ResourceMediaTypes media){
		assert media.mediaTypes!=null
		assert media.mediaTypes.size()>=0
		for(def r : media.mediaTypes){
		assert r.name != null
		assert r.version =~ /[0-9]/
		assert r.deprecated != null
		}
		return true
	}
	
	/**
	 * Method to verify common APIMeta_Resources items:
	 * 1. /apiMeta/resources is not null
	 * 2. the resources link size is valid
	 * 3. Every resource link has a valid:
	 * 		3a. Method
	 * 		3b. Rel
	 * 		3c. Href
	 * 		3d. Uri
	 * @param resources a Links object which contains a list of links
	 * @return True if the resources are valid, if the resources are not valid, an
	 * exception would be thrown
	 */
	public static boolean verifyAPIMetaResources(Links resources){
		assert resources !=null
		assert resources.links.size() =~ /[0-9]/
		for(def l : resources.links){
			assert l.method !=null
			assert l.rel !=null
			assert l.href !=null
			assert l.uri !=null
		}
		return true
	}
	
	/**
	 * Method to verify common APIMeta_Version items:
	 * 1. /apiMeta/version is not null
	 * 2. The version number is valid
	 * @param version a String whos contents are checked
	 * @return True if the version is valid, if the version is not valid, an
	 * exception would be thrown
	 */
	public static boolean verifyAPIMetaVersion(String version){
		int versionNum=0
		if(version.contains(".")){versionNum = Integer.parseInt(version.substring(version.indexOf("value\":\"")+8, version.indexOf(".")))}
		else{ versionNum = Integer.parseInt(version.substring(version.indexOf("value\":\"")+8, version.lastIndexOf("\"")))}
		assert versionNum =~  /[0-9]/
		return true
	}
	
	/**
	 * Method to verify common APIMeta_Versions items:
	 * 1. /apiMeta/versions is not null
	 * 2. The size is valid
	 * 3. Every field and version is not null
	 * @param versions an ResourceProperties object who's contents are verified
	 * @return True if the versions are valid, if the versions are not valid, an
	 * exception would be thrown
	 */
	public static boolean verifyAPIMetaVersions(ResourceProperties versions){
		List<String> fields=new ArrayList<>();
		versions.toString().substring(1,versions.toString().lastIndexOf("}")).split(",").each{fields.add(it.substring(0, it.indexOf("=")).trim())}
		assert versions.size()  =~ /[0-9]/
       fields.each{
		   assert it != null
		   assert versions[it] =~ /[0-9]/
	   }
	   return true
	}
}