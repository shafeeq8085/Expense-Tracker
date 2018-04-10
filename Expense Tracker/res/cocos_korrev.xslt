<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" version="1.0" encoding="ISO-8859-1" />
	<!-- Start -->
										<xsl:for-each select="current()/repro">										
										<xsl:element name="img">
											<xsl:attribute name="type">image</xsl:attribute>	
											<xsl:attribute name="src"><xsl:value-of select="/korrespondenzverlauf/resource/@repro"/></xsl:attribute><xsl:text>&#160;</xsl:text>
										</xsl:element>
										<xsl:value-of select="current()" />
										<xsl:if test="position() != last()">
											<br />
										</xsl:if>
									</xsl:for-each>
									<xsl:for-each select="current()/duplikat">										
										<xsl:element name="img">
											<xsl:attribute name="type">image</xsl:attribute>	
											<xsl:attribute name="src"><xsl:value-of select="/korrespondenzverlauf/resource/@duplikat"/></xsl:attribute><xsl:text>&#160;</xsl:text>
										</xsl:element>
										<xsl:value-of select="current()" />
										<xsl:if test="position() != last()">
											<br />
										</xsl:if>
									</xsl:for-each>
	<xsl:template match="/">
		<html>
			<head>
				<title>Cocos-Auskunft</title>						
																		
			</head>
			<body>				
				<form>
					<xsl:call-template name="korrev-eintrag" />
				</form>
			</body>
		</html>
	</xsl:template>
	<!-- **************** Personendaten ****************  -->
	<xsl:template name="korrev-eintrag">
		<table border="0" cellspacing="0" cellpadding="0" width="100%">
			<xsl:if test="count(/korrespondenzverlauf/eintrag) > 0">
				<xsl:for-each select="/korrespondenzverlauf/eintrag">
					<tr>
						<xsl:attribute name="id">#posTr<xsl:value-of select="position()" />	
						</xsl:attribute>
						<xsl:if test="round(position() mod 2) = 0">
							<xsl:attribute name="bgcolor">#eeeeee</xsl:attribute>
						</xsl:if>
						<td valign="top"  width="5"><font face="Helvetica, Arial, sans-serif"><b><font><xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b></font></td>
  			     		 <xsl:call-template name="td-separator"/>	
		
						<xsl:call-template name="td-separator" />											
						<xsl:call-template name="firstresultfield">	
							<xsl:with-param name="field" select="current()/@sino" />
							<xsl:with-param name="width" select="60" />
							<xsl:with-param name="strong" select="current()/@strong" />						
						</xsl:call-template>
						
						<xsl:call-template name="td-separator" />											
						<xsl:call-template name="firstresultfield">	
							<xsl:with-param name="field" select="current()/@datum" />
							<xsl:with-param name="width" select="95" />
							<xsl:with-param name="strong" select="current()/@strong" />						
						</xsl:call-template>

						<xsl:call-template name="td-separator" />
						<td valign="top" width="145">
							<xsl:attribute name="id">#posItem<xsl:number />	
							</xsl:attribute>
							<font face="Helvetica, Arial, sans-serif">
								
									<a>
										<xsl:attribute name="color">blue</xsl:attribute>
										<xsl:attribute name="href">
											#posItem
											<xsl:number />
										</xsl:attribute>
										<xsl:value-of select="current()/@item" />
									</a>
								
							</font>
						</td>

						<xsl:call-template name="td-separator" />
						<xsl:call-template name="resultfield">
							<xsl:with-param name="field" select="current()/@amount" />
							<xsl:with-param name="width" select="100" />
							<xsl:with-param name="strong" select="current()/@strong" />
						</xsl:call-template>
						
						<xsl:call-template name="td-separator" />
						<xsl:call-template name="resultfield">
							<xsl:with-param name="field" select="current()/@cashind" />
							<xsl:with-param name="width" select="130" />
							<xsl:with-param name="strong" select="current()/@strong" />
						</xsl:call-template>

					</tr>
					<xsl:if test="current()/notiz or current()/@beginn or current()/@ende">
						<tr>
							<xsl:if test="round(position() mod 2) = 0">
								<xsl:attribute name="bgcolor">#eeeeee</xsl:attribute>
							</xsl:if>
							<td valign="top"></td>
							<xsl:call-template name="td-separator" />
							<td valign="top"></td>
							<xsl:call-template name="td-separator" />
							<td valign="top" colspan="7">
								<font face="Helvetica, Arial, sans-serif">
									<xsl:for-each select="current()/notiz">
										<xsl:value-of select="current()" />
										<xsl:if test="position() != last()">
											<br />
										</xsl:if>
									</xsl:for-each>
								</font>
							</td>
							<xsl:call-template name="td-separator" />
							<td valign="top">
								<font face="Helvetica, Arial, sans-serif">
									<xsl:value-of select="current()/@beginn" />
								</font>
							</td>
							<xsl:call-template name="td-separator" />
							<td valign="top">
								<font face="Helvetica, Arial, sans-serif">
									<xsl:value-of select="current()/@ende" />
								</font>
							</td>
							<xsl:call-template name="td-separator" />
							<td valign="top"></td>
							<td valign="top"></td>
						</tr>
					</xsl:if>					
				</xsl:for-each>		
				<xsl:if test="/korrespondenzverlauf/resource/@addtr">	
					<tr></tr>
				</xsl:if>	
			</xsl:if>
		</table>
	</xsl:template>
	<!-- ****************  Utilities ****************  -->
	<xsl:template name="firstresultfield">
		<xsl:param name="field" />
		<xsl:param name="width" />
		<xsl:param name="strong" />		
		<xsl:if test="($strong)">
			<td valign="top" width="{$width}">
				<font face="Helvetica, Arial, sans-serif">							
					<strong><xsl:value-of select="$field" />
					</strong>
				</font>
			</td>
		</xsl:if>
		<xsl:if test="not($strong)">
			<td valign="top" width="{$width}">			
			<font face="Helvetica, Arial, sans-serif">				
				<xsl:value-of select="$field" />
			</font>
			</td>
		</xsl:if>
	</xsl:template>
	<xsl:template name="resultfield">
		<xsl:param name="field" />		
		<xsl:param name="width" />
		<xsl:param name="strong" />	
		<xsl:if test="($strong)">
			<td valign="top" width="{$width}">			
				<font face="Helvetica, Arial, sans-serif"><strong><xsl:value-of select="$field" /></strong>				
				</font>
			</td>
		</xsl:if>
		<xsl:if test="not($strong)">
			<td valign="top" width="{$width}">				
				<font face="Helvetica, Arial, sans-serif"><xsl:value-of select="$field" />						
				</font>
			</td>
		</xsl:if>
	</xsl:template>
	<xsl:template name="resultfieldwithtooltip">
		<xsl:param name="field" />
		<xsl:param name="tooltip" />
		<xsl:param name="width" />
		<xsl:param name="strong" />	
		<xsl:if test="($strong)">
			<td valign="top" width="{$width}">	
				<xsl:attribute name="tooltip"><xsl:value-of select="$tooltip" /></xsl:attribute>		
				<font face="Helvetica, Arial, sans-serif"><strong><xsl:value-of select="$field" /></strong>				
				</font>
			</td>
		</xsl:if>
		<xsl:if test="not($strong)">
			<td valign="top" width="{$width}">	
				<xsl:attribute name="tooltip"><xsl:value-of select="$tooltip" /></xsl:attribute>							
				<font face="Helvetica, Arial, sans-serif"><xsl:value-of select="$field" />						
				</font>
			</td>
		</xsl:if>
	</xsl:template>
	<xsl:template name="td-separator">
		<td valign="top">
			<xsl:text disable-output-escaping="yes"><![CDATA[&nbsp;]]></xsl:text>
		</td>
	</xsl:template>	
	<xsl:template name="nblanks">
		<xsl:param name="num" />
		<xsl:if test="$num &gt; 0">
			<xsl:text disable-output-escaping="yes"><![CDATA[&nbsp;]]></xsl:text><xsl:call-template name="nblanks"><xsl:with-param name="num" select="$num - 1" /></xsl:call-template>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>
