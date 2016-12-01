README
======

Playing with SOLR document transformations.

To test manually, use a minimal SOLR setup, e.g. https://github.com/hsch/solr-minimal.

Copy artefact, so SOLR can load it:

```shell
$ cp target/docamp-1.0-SNAPSHOT.jar ../../path/to/solr/contrib/lib
```

Adjust solrconfig.xml:

```xml
...
<lib dir="../../contrib/lib" regex=".*\.jar" />

<transformer name="example" class="de.ubleipzig.solr.transform.HelloTransformerFactory">
    <str name="location">Russia</str>
</transformer>
...
```

In the startup logs you should see something like:

```shell
1162 [coreLoadExecutor-3-thread-1] INFO  org.apache.solr.core.SolrResourceLoader  - ...
   Adding 'file:/xxxx/xxxx/code/hsch/solr-minimal/contrib/lib/docamp-1.0-SNAPSHOT.jar' to classloader
```

Basic query:

```json
{
  "responseHeader": {
    "status": 0,
    "QTime": 24
  },
  "response": {
    "numFound": 2,
    "start": 0,
    "docs": [
      {
        "id": "1",
        "name": "alice",
        "ExampleTransformer": "From Russia with Love!"
      },
      {
        "id": "2",
        "name": "bob",
        "ExampleTransformer": "From Russia with Love!"
      }
    ]
  }
}
```
