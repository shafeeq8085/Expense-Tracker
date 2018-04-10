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
                  <td valign="top"  width="40"><font face="Helvetica, Arial, sans-serif"><b><font><xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b></font></td>
  			     		 <xsl:call-template name="td-separator"/>	
			    </tr>
            
			    <tr bgcolor="#D4D2CC">	
			      <td valign="top"  width="20"><font face="Helvetica, Arial, sans-serif"><b><font><xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b></font></td>
  			     		 <xsl:call-template name="td-separator"/>		      
			      <td valign="top"  width="40"><font face="Helvetica, Arial, sans-serif"><b><font>SI NO<xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b><xsl:call-template name="nblanks"></xsl:call-template></font></td>
  			     		 <xsl:call-template name="td-separator"/>
  			      <td valign="top"  width="20"><font face="Helvetica, Arial, sans-serif"><b><font><xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b></font></td>
  			     		 <xsl:call-template name="td-separator"/>		      		 
			      <td   valign="top" width="60"><font face="Helvetica, Arial, sans-serif"><b><font>DATE<xsl:call-template name="nblanks"><xsl:with-param name="num" select="1"/></xsl:call-template></font></b></font></td>
  			     		 <xsl:call-template name="td-separator"/>
  			      <td valign="top"  width="20"><font face="Helvetica, Arial, sans-serif"><b><font><xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b></font></td>
  			     		 <xsl:call-template name="td-separator"/>		      
			      <td   valign="top" width="100"><font face="Helvetica, Arial, sans-serif"><b><font>BANK<xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b></font></td>
  			      		<xsl:call-template name="td-separator"/>
  			      <td valign="top"  width="20"><font face="Helvetica, Arial, sans-serif"><b><font><xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b></font></td>
  			     		 <xsl:call-template name="td-separator"/>		      
   				  <td   valign="top" width="60"><font face="Helvetica, Arial, sans-serif"><b><font>AMOUNT<xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b></font></td>
  			      		<xsl:call-template name="td-separator"/>
  			      <td valign="top"  width="20"><font face="Helvetica, Arial, sans-serif"><b><font><xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b></font></td>
  			     		 <xsl:call-template name="td-separator"/>		      			      
   			      <td   valign="top" width="60"><font face="Helvetica, Arial, sans-serif"><b><font>TYPE<xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b></font></td>		      
  			     		 <xsl:call-template name="td-separator"/>
  			      <td valign="top"  width="20"><font face="Helvetica, Arial, sans-serif"><b><font><xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b></font></td>
  			     		 <xsl:call-template name="td-separator"/>		      			   			      
			    </tr>
			    
                <tr bgcolor="#D4D2CC">	   
                  <td valign="top"  width="40"><font face="Helvetica, Arial, sans-serif"><b><font><xsl:call-template name="nblanks"><xsl:with-param name="num" select="0"/></xsl:call-template></font></b></font></td>
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
    		<xsl:attribute name="id">#sortDesc<xsl:value-of select="$criteria"/></xsl:attribute>
        	<xsl:attribute name="src"><xsl:value-of select="/korrespondenzverlauf/resource/@sortDesc"/></xsl:attribute>    		 
       	</xsl:element>        	
   </xsl:template>
   
   <xsl:template name="sortAsc">    
   		<xsl:param name="criteria"/>
    	<xsl:element name="img">
    		<xsl:attribute name="id">#sortAsc<xsl:value-of select="$criteria"/></xsl:attribute> 
        	<xsl:attribute name="src"><xsl:value-of select="/korrespondenzverlauf/resource/@sortAsc"/></xsl:attribute>       		
       	</xsl:element>          	
    </xsl:template>
</xsl:stylesheet>
