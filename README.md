# DocBook link tester Maven plugin

This is a simple Maven plugin allowing you to validate external (and internal) links within your DocBook based documentation. The plugin will look for &lt;link&gt; and &lt;olink&gt; elements and will handle them accordingly:

* For &lt;olink&gt; elements or &lt;link&gt; elements with olink `role` (in the format of targetdoc#targetptr), the plugin will first look up all the xml:id attributes throughout the document. If the targetdoc exists and has the references targetptr, then the olink was valid.
* If it's a regular link, an HttpURLConnection will be opened, and if the response code is less than 400, the link is considered valid.

Since version 2.0.0 the plugin also validates olinks stored in &lt;olink&gt; XML elements. The targetdoc and targetptr attributes must be present for such elements for the validation to work correctly.

## Sample Configuration

In the pom.xml file you need to add the following code block within the <configuration> tag, to each build-plugins list:

```
  <skipUrlPatterns>
   <skipUrlPattern>^https://bugster.forgerock.org/jira/browse/OPEN(AM|ICF|IDM|IG|DJ)-[0-9]{1,4}$</skipUrlPattern>
   <skipUrlPattern>^http(s)?://www.example.*$</skipUrlPattern>
   <skipUrlPattern>^http(s)?://example.*$</skipUrlPattern>
  </skipUrlPatterns>
```

This will bind the plugin invocation to the pre-site phase. About the configuration options:

* skipUrlPatterns - This list expects a set of valid [Patterns](http://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html). URLs matching any of the defined patterns will be excluded from validation. Useful for release notes, with bunch of similarly structured URLs (especially if it's generated).

## Execution

By setting the execution in the pom.xml the plugin will be invoked during the build, but if you only want to execute the plugin every now and then, you can use the following command:

```
mvn org.forgerock.maven.plugins:linktester-maven-plugin:2.0.0-SNAPSHOT:check
```

To check out the plugin's help, run:

```
mvn org.forgerock.maven.plugins:linktester-maven-plugin:2.0.0-SNAPSHOT:help
```

## License

Everything in this repo is licensed under the ForgeRock CDDL license: http://forgerock.org/license/CDDLv1.0.html
