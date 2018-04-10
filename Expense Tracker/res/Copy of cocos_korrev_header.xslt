<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" version="1.0" encoding="ISO-8859-1" />

	<!-- Start -->	
	<xsl:template match="/">
		<html>
			<head>
				<title>Cocos-Auskunft-Header</title>
			</head>
			<body>
			<form>
			    <xsl:call-template name="korrev-header"/>
		    </form>
			</body>
		</html>
	</xsl:template>
	
	<!-- **************** Personendaten ****************  -->	

	<xsl:template name="korrev-header">

		<table border="0" cellspacing="0" cellpadding="0" width="100%">  		 
          
            
			    <tr bgcolor="#D4D2CC">
			      <td valign="top"  width="30"><font face="Helvetica, Arial, sans-serif"><b><font><xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b></font></td>
  			     		 <xsl:call-template name="td-separator"/>
			      <td valign="top"  width="80"><font face="Helvetica, Arial, sans-serif"><b><font>Datum<xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b><xsl:call-template name="nblanks"></xsl:call-template></font></td>
  			     		 <xsl:call-template name="td-separator"/>
			      <td   valign="top" width="150"><font face="Helvetica, Arial, sans-serif"><b><font>Art<xsl:call-template name="nblanks"><xsl:with-param name="num" select="1"/></xsl:call-template>d.<xsl:call-template name="nblanks"><xsl:with-param name="num" select="1"/></xsl:call-template>Info</font></b></font></td>
  			     		 <xsl:call-template name="td-separator"/>
			      <td   valign="top" width="270"><font face="Helvetica, Arial, sans-serif"><b><font>Inhaltsangabe<xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b></font></td>
  			      		<xsl:call-template name="td-separator"/>
   				  <td   valign="top" width="80"><font face="Helvetica, Arial, sans-serif"><b><font>Abt.<xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b></font></td>
  			      		<xsl:call-template name="td-separator"/>			      
   			      <td   valign="top" width="80"><font face="Helvetica, Arial, sans-serif"><b><font>SbNr<xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b></font></td>		      
  			     		 <xsl:call-template name="td-separator"/>			 
			      <td   valign="top" width="80"><font face="Helvetica, Arial, sans-serif"><b><font>Dok-Typ<xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b></font></td>
  			      		<xsl:call-template name="td-separator"/>
  			      <td  valign="top" width="80"><font face="Helvetica, Arial, sans-serif"><b><font>Status<xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b></font></td>
  			      		<xsl:call-template name="td-separator"/>
  			      
			    </tr>
			    
                <tr bgcolor="#D4D2CC">
			      <td valign="top"></td>
  			     	 <xsl:call-template name="td-separator"/>
  			      <td valign="top"></td>
  			     	 <xsl:call-template name="td-separator"/>	
			      <td colspan="7" valign="top"><b><font face="Helvetica, Arial, sans-serif">Notiz</font></b></td>
  			     	 <xsl:call-template name="td-separator"/>
	          	  <td valign="top"><font face="Helvetica, Arial, sans-serif"><b><font>Beginn</font></b></font></td>
  			     	 <xsl:call-template name="td-separator"/>
	          	  <td valign="top"><font face="Helvetica, Arial, sans-serif"><b><font>Ende</font></b></font></td>
  			     	 <xsl:call-template name="td-separator"/>
  		     	 <td valign="top"></td>
  		     	 <td valign="top"></td>
                </tr>
                <tr bgcolor="#D4D2CC">
			      <td valign="top"  width="10"></td>
  			     		 <xsl:call-template name="td-separator"/>
			      <td valign="top" width="80"><xsl:call-template name="sortDesc"/><xsl:call-template name="sortAsc"><xsl:with-param name="criteria" select="Datum"/></xsl:call-template></td>
  			     		 <xsl:call-template name="td-separator"/>
			      <td   valign="top" width="150"><xsl:call-template name="sortDesc"/><xsl:call-template name="sortAsc"/></td>
  			     		 <xsl:call-template name="td-separator"/>
			      <td   valign="top" width="160"><xsl:call-template name="sortDesc"/><xsl:call-template name="sortAsc"/></td>
  			      		<xsl:call-template name="td-separator"/>
			      <td   valign="top" width="20"><xsl:call-template name="sortDesc"/><xsl:call-template name="sortAsc"/></td>
  			      		<xsl:call-template name="td-separator"/>			      
			      <td   valign="top" width="20"><xsl:call-template name="sortDesc"/><xsl:call-template name="sortAsc"/></td>
  			      		<xsl:call-template name="td-separator"/>
			      <td   valign="top" width="20"><xsl:call-template name="sortDesc"/><xsl:call-template name="sortAsc"/></td>
  			      		<xsl:call-template name="td-separator"/>
   			      <td   valign="top" width="20"><xsl:call-template name="sortDesc"/><xsl:call-template name="sortAsc"/></td>		      
  			     		 <xsl:call-template name="td-separator"/>

  			      
			    </tr>
       
				
				
		</table>
		
	</xsl:template>

	<!-- ****************  Utilities ****************  -->	
	<xsl:template name="td-separator">
        <td valign="top"><xsl:text disable-output-escaping="yes"><![CDATA[&nbsp;]]></xsl:text></td>
	</xsl:template>

	<xsl:template name="nblanks">
	    <xsl:param name="num"/>
	    <xsl:if test="$num &gt; 0">
           <xsl:text disable-output-escaping="yes"><![CDATA[&nbsp;]]></xsl:text>
           <xsl:call-template name="nblanks">
               <xsl:with-param name="num" select="$num - 1"/>
           </xsl:call-template>
	    </xsl:if>
	</xsl:template>
    
     <xsl:template name="sortDesc"> 
     	<xsl:param name="criteria"/>
    	<xsl:element name="img">
        		<xsl:attribute name="src"><xsl:value-of select="/korrespondenzverlauf/resource/@sortDesc"/></xsl:attribute>
    		   <xsl:attribute name="id">#sortDesc<xsl:value-of select="$criteria"/></xsl:attribute> 
       	</xsl:element> 
    </xsl:template>
   <xsl:template name="sortAsc">    
    	<xsl:element name="img">
        	<xsl:attribute name="src"><xsl:value-of select="/korrespondenzverlauf/resource/@sortAsc"/></xsl:attribute>
       	</xsl:element>   
    </xsl:template>
</xsl:stylesheet>
