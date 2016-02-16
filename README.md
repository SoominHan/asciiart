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

### Chart

#### Chart example 1

A chart can be plotted on an ASCII raster.

A function is expressed either:

* by implementing the CurveModel interface (Java 6/7)
* by providing a closure

Here is an example using a Java 8 closure:

```java
ChartWidget widget = new ChartWidget(
        new TextBoxSize(80, 12),
        x -> Math.cos(x - 1)
        , -1, 10);

// Create a character raster that will store the ASCII outputs
CharacterRaster raster = new ExtensibleCharacterRaster(' ');
// Render the widget on the character raster
widget.render(new RasterContext(raster));
// Now we display the raster contents in the console output
System.out.println(raster.toString());
```

Output is:

```

       ^                                                                        
       |  ---------                                     ---------               
       |--         --\                                --         --             
      //              \                             /-             \\           
     / |               \\                          /                 \          
   //  |                 \                       //                   \\        
../....+..................\\..................../.......................\......>
/      |                    \                 //                         \\     
       |                     \\             //                             \-   
       |                       --         --                                 -- 
       |                         ---- ----                                     -
       |                             -                                          

```

#### Chart example 2

The closure or CurveModel object does not need to be a mathematical function, we can
also fetch data from any existing source, like an integer list in the following example:

```java
List<Integer> values = new ArrayList<>();
values.add(32);
values.add(56);
values.add(-12);
values.add(24);

ChartWidget widget = new ChartWidget(
        new TextBoxSize(80, 12),
        x -> (double) values.get((int) x)
        , 0, 4);
Raster raster = new ExtensibleCharacterRaster(' ');

widget.render(new RasterContext(raster));
System.out.println(raster.toString());
```

```
^                   --------------------                                        
|                                                                               
|                                                                               
|                                                                               
--------------------                                                            
|                                                           --------------------
|                                                                               
|                                                                               
|                                                                               
+..............................................................................>
|                                                                               
|                                       --------------------                    
```   


### Image to text

Images can be directly rendered as strings...

```java
File image = new File("/home/yk/Desktop/Octocat.png");
BufferedImage circleImage = ImageIO.read(image);

TextWidget widget = new PictureWidget(new TextBoxSize(80, 30),
        circleImage, new ShapeRasterizer());
Raster raster = new ExtensibleCharacterRaster();

widget.render(new RasterContext(raster));
System.out.println(raster);
```

```
                     ___,                             ___                       
                    ,3B#Z@Kg_       _,___,_,     ,yg@BWBNj                      
                    {@&E#QBBZWKgWQBBZBKB9QE@ZBWgBKBWDE@ZBB                      
                    MMZMKB@QRBZMKBg&B@QRBZMKB@&B@QRBZMKB@&                      
                    ^W3BKBB6B@QBW3BKBB6BBQBW3BKBB6BBQBW3BK                      
                   _#$BMUBEWBB@M$BQUBEWBB@M$BEUBE@BB@M$BEUB_                    
                  ;B$KRMQWQZBKB$QRMQWQZBK@MQRMQMQZBK@MQRMQMQ1                   
                  ZMK@9QW9Z#BZQK@9QW9Z#K@MK@9QVBZ#K@MKB9QVBZ#y                  
                 {9@KMWQMWMPOO]VVXMPOTPMTBTB3TO6QUTMVKM@QBBDWj                  
                 MBQQBg9KU^^      /Y)]^^,     /YY'^^/^T9#BS#E&                  
                 3Q#BgBT;;,  __g,  ,,;\\\\^^`  _y,, \''TBjB@Kj                  
                  Q9WEB.\.   ip]]    `^^^,;;;  O_Bp  ,,,d3BSWF                  
       ,  .,. ,.  ]Q9WZp^,;  }}Bd ];;,   L ^^) KRZ#   ']jB@MBU ., .. ,,,_       
        _ ,  .     fBZVQ^,   ` " ;]]^  y,   L]]  `    L\M@QQ   ...  ,, _        
      `             ^OQ@$C''//,],,   L;;\;Y.,^,^  C''/jKB@T               `     
                      `MMBKgj; \\^`^^ ^d;,,L\'')^/_/g$MPO^                      
                ay-_      ^TMMQ#@WgQpjQPggggpMg#$KMPY``                         
                 M9KW;          _#BQRBBQBB@KB9g_                                
                    QQ\_       yMBKBBU9MZQ@BB&MB_                               
                     M@jDjjg g3DBWXQWQB^[K9W3K$BD                               
                      `TMfQPQQMQQBI BUB\[&QE N#B]                               
                               9QEk^#BgFgBD@ 9@9g                               
                               BD@^ @9QFXBB@ $W9D                               
                               ZBKj BQB [B3B KBBQ                               
                         `   ,,WBBF.BWBk3KW9 3BQW_` .                           
                            a@WPP' _QQB fBZQ Y^TfByy  ` ,   .                   
                    ,   `      , ._#9UT  V06Bj         `                        
                              :`      `   ` `Y `      .                         
                           .       ,   .      ,    .                            
                                      `   `                                     

```

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