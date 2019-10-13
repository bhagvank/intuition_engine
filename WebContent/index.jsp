<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<header>
<script language="JavaScript" type="text/javascript">

function addField() {

	var textbox = document.getElementById('fieldName');
	
	var textValue = textbox.value;
	
	//alert(textValue)
	
	//Create an input type dynamically.
	var element = document.createElement("input");

	//Create Labels
	var label = document.createElement("Label");
	label.innerHTML = textValue;     

	//Assign different attributes to the element.
	element.setAttribute("type", "text");
	element.setAttribute("value", "");
	element.setAttribute("name", textValue);
	element.setAttribute("style", "width:200px");

	label.setAttribute("style", "font-weight:normal");

	// 'foobar' is the div id, where new fields are to be added
	var foo = document.getElementById("primary");

	//Append the element in page (in span).
	foo.appendChild(label);
	foo.appendChild(element);
	foo.appendChild(document.createElement('br'));
	}
 function roll(obj, highlightcolor, textcolor){
                obj.style.backgroundColor = highlightcolor;
                obj.style.color = textcolor;
            }
function appendColumn()
{var tbl=document.getElementById('my-table');

 var txtbox = document.getElementById("column_text");
    var value = txtbox.value;
for(var i=0;i<tbl.rows.length;i++)
createCell(tbl.rows[i].insertCell(tbl.rows[i].cells.length),value,'col');
}

function appendRow()
{ var tbl=document.getElementById('my-table');

  var txtbox = document.getElementById("row_text");
    var value = txtbox.value;
  var row=tbl.insertRow(tbl.rows.length);
  for(var i=0;i<tbl.rows[0].cells.length;i++)
   createCell(row.insertCell(i),value,'row');
}

function createCell(cell,text,style)
{
  var div=document.createElement('div');
 var txt=document.createTextNode(text);
 div.appendChild(txt);
 div.setAttribute('class',style);
 div.setAttribute('className',style);
 cell.appendChild(div);
 cell.addEventListener("mouseover",function(event) {roll(this, 'yellow', 'black');});
 cell.addEventListener("mouseout", function(event) {roll(this, 'darkcyan','black');});
 }

function deleteRows()
{
  var tbl=document.getElementById('my-table');
  var lastRow=tbl.rows.length-1;
  for(var i=lastRow;i>0;i--)tbl.deleteRow(i);

  }

function deleteColumns()
{
    var tbl=document.getElementById('my-table');
    var lastCol=tbl.rows[0].cells.length-1;
    for(var i=0;i<tbl.rows.length;i++)
    for(var j=lastCol;j>0;j--)tbl.rows[i].deleteCell(j);

}
</script>
<div>
<style>
div#my-container input{padding:5px;font-size:12px !important;width:100px;margin:2px}
table#my-table{border-collapse:collapse;width:0%}
table#my-table td{width:50px;height:27px;border:1px solid #D3D3D3;text-align:center;padding:0;}
.append_row{color:black !important;background-color:#eee !important;border:1px #ccc solid !important;}
.append_column{color:black !important;background-color:#eee !important;border:1px #ccc solid !important;}
.delete{color:black !important;background-color:#eee !important;border:1px #ccc solid !important;}
.col{background-color:white !important}
.row{background-color:white !important}
</style></div>
</header>
<body>
<input id="fieldName" type="text" name="fieldName">
<br>
<input type="button" name="field" value="Add field" onclick="javascript:addField()">
<div id="primary" class="content-area grid-parent grid-75 tablet-grid-75" itemprop="mainContentOfPage">
<table>

</table>

</div>
</body>