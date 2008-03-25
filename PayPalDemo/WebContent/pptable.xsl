<?xml version="1.0" encoding="UTF-8"?>
<?xsl-stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"?>
<xsl:output method="html" />
<xsl:template method="/table">
	<html>
		<head>
			<title>
				<xsl:value-of select="orders" />
			</title>
			<link rel="stylesheet" href="table.css" type="text/css" />
		</head>
		<body>
			<h1>
				<xsl:value-of select="title" />
			</h1>
			<h2>
				<xsl:apply-templates select="games" />
				<xsl:call-template name="email" />
			</h2>

		</body>
	</html>
</xsl:template>
<xsl:template match="orders">
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Action</th>
				<th>Messages</th>
			</tr>
			>
		</thead>
		<tbody>
			<xsl:for-each select="order">
				<xsl:attribute name="class">
					<xsl:choose>
						<xsl:when test="position() mod 2 = 1">
							<xsl:text>odd-row</xsl:text>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>even-row</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:attribute>
				<td>
					<xsl:value-of select="name" />
					<xsl:value-of select="price" />
					<xsl:value-of select="quantity" />
					<xsl:value-of select="action" />
					<xsl:value-of select="messages" />
				</td>
			</xsl:for-each>
		</tbody>
	</table>
</xsl:template>
<xsl:template name="email">
	<br />
	<hr />
	<script language="JavaScript">
		<![CDATA[
var protocol="mailto"
var user="webmaster"
var server="ppDemo.com";
function contact(){
	window.open(protocol+";"+user+"@"+server,"_self");}
]]>
	</script>

	<p>
		For questions about this page contact
		<a href="javascript:contact()">webmaster at ppDemo.com</a>
	</p>
</xsl:template>

</xsl:stylesheet>












