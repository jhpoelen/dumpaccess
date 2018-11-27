# dumpaccess

Dumps Access database files to tab-separated values files. 

# prereqs

Java 8+ , [Apache Maven](https://maven.apache.org) 

# build

To build a standalone jar, run ```mvn package```.


# run

```java -jar target/dumpaccess-0.1-SNAPSHOT-jar-with-dependencies.jar help```

to show usage. 

```java -jar target/dumpaccess-0.1-SNAPSHOT.jar-with-dependencies.jar dump-long -d some.accdb | head -n10 > some.tsv```

# usage

```
Usage: <main class> [command] [command options]
  Commands:
    tables      List Available Tables
      Usage: tables [options]
        Options:
        * --db-file, -d
            database file
          --limit, -n
            limit number of rows

    dump-wide      Dump database table to wide tsv (one line per table row)
      Usage: dump-wide [options] table
        Options:
        * --db-file, -d
            database file
          --limit, -n
            limit number of rows

    dump-long      Dump database to long tsv (one line per table/row/column
            value).
      Usage: dump-long [options]
        Options:
        * --db-file, -d
            database file
          --limit, -n
            limit number of rows

    version      Show Version
      Usage: version

```
