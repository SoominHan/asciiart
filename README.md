## ASCII Art For Java

Instantly create ASCII reports, multi-column tables, graphs, ...

These can be rendered on screen, files and most importantly log streams.

## Quick Start

Add the Ascii Art library to your project. The jar file is available for download and the latest 
release is also available as a Maven artefact from Maven central repository in group org.alcibiade.

```xml
<dependency>
        <groupId>org.alcibiade</groupId>
        <artifactId>asciiart-core</artifactId>
        <version>1.1.0</version>
</dependency>
```

Have a look at the code samples below to get examples on how to render tables or other compositions.

## Code Samples

### Table

The TableWidget can be used to handle data grids. Data sources should implement
the TableModel interface. Several useful implementations are provided out of the
box.
Cell contents are converted to their respective text representations using their
toString() method. Numbers are automatically aligned horizontally in a smart
way.

#### List to table

The TableModelCollectionAdapter can be used to wrap a collection of items
in a single column table.

```
+--------+
| Fruit  |
+--------+
| Orange |
| Apple  |
| Lemon  |
+--------+
```

Source code:

```java
    List<String> items = new ArrayList<String>();
    items.add("Orange");
    items.add("Apple");
    items.add("Lemon");

    TableModel tableModel = new TableModelCollectionAdapter(items, "Fruit");
    TableWidget tableWidget = new TableWidget(tableModel);

    CharacterRaster raster = new ExtensibleCharacterRaster(' ');
    tableWidget.render(new RasterContext(raster));

    System.out.println(raster);
```

#### Map to table

The TableModelCollectionAdapter can be used to wrap a collection of items
in a single column table.

```
+-----------+-----------+
| City      | Value     |
+-----------+-----------+
| Amsterdam |    3.1415 |
| London    | 2012.0    |
| Paris     |   42.0    |
+-----------+-----------+

Source code:

```java
    Map<String, Double> items = new TreeMap<String, Double>();
    items.put("Paris", 42.);
    items.put("London", 2012.);
    items.put("Amsterdam", 3.1415);

    TableModel tableModel = new TableModelMapAdapter(items, "City", "Value");
    TableWidget tableWidget = new TableWidget(tableModel);

    CharacterRaster raster = new ExtensibleCharacterRaster(' ');
    tableWidget.render(new RasterContext(raster));

    System.out.println(raster);
```

#### Custom table

A custom TableModel implementation may directly 
extend AbstractTableModel.

```
+----------+----------+----------+----------+
| Column 0 | Column 1 | Column 2 | Column 3 |
+----------+----------+----------+----------+
| 00:00    | 01:00    | 02:00    | 03:00    |
| 00:01    | 01:01    | 02:01    | 03:01    |
| 00:02    | 01:02    | 02:02    | 03:02    |
| 00:03    | 01:03    | 02:03    | 03:03    |
| 00:04    | 01:04    | 02:04    | 03:04    |
| 00:05    | 01:05    | 02:05    | 03:05    |
+----------+----------+----------+----------+
```

Source code:

```java
    TableWidget tableWidget = new TableWidget(new CustomTableModelImpl());

    CharacterRaster raster = new ExtensibleCharacterRaster(' ');
    tableWidget.render(new RasterContext(raster));

    System.out.println(raster);
```

```
class CustomTableModelImpl extends AbstractTableModel {

    @Override
    public int getWidth() {
        return 4;
    }

    @Override
    public int getHeight() {
        return 6;
    }

    @Override
    public String getCellContent(int x, int y) {
        return String.format("%02d:%02d", x, y);
    }

    @Override
    public String getColumnTitle(int x) {
        return "Column " + x;
    }
}
```

### Direct usage with SLF4j

You should include the asciiart-slf4j and its dependencies
in your project. If you use maven:

```xml
    <dependency>
        <groupId>org.alcibiade</groupId>
        <artifactId>asciiart-slf4j</artifactId>
        <version>0.1-SNAPSHOT</version>
    </dependency>
```

Sample:

```
22:34:55.331 [main] INFO  org.alcibiade.asciiarttest.App - Here is a basic log line
22:34:55.348 [main] INFO  org.alcibiade.asciiarttest.App - +--------+
22:34:55.348 [main] INFO  org.alcibiade.asciiarttest.App - | Fruits |
22:34:55.348 [main] INFO  org.alcibiade.asciiarttest.App - +--------+
22:34:55.348 [main] INFO  org.alcibiade.asciiarttest.App - | Orange |
22:34:55.348 [main] INFO  org.alcibiade.asciiarttest.App - | Apple  |
22:34:55.348 [main] INFO  org.alcibiade.asciiarttest.App - | Lemon  |
22:34:55.348 [main] INFO  org.alcibiade.asciiarttest.App - +--------+
```

Source code:

```java
    List<String> items = new ArrayList<String>();
    items.add("Orange");
    items.add("Apple");
    items.add("Lemon");

    AsciiArtLogger logger = new AsciiArtLogger(LoggerFactory.getLogger(App.class));
    logger.info("Here is a basic log line");
    logger.info(items, "Fruits");
```