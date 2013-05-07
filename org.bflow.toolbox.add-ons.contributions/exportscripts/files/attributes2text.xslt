<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:xmi="http://www.omg.org/XMI"
xmlns:notation="http://www.eclipse.org/gmf/runtime/1.0.1/notation"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
xmlns:edu.toronto.cs.openome_model="http:///edu/toronto/cs/openome_model.ecore">
<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
<xsl:template match="attributes" name="attributes">
	<xsl:variable name="attributeId">
		<xsl:value-of select="string(@id)"/>
	</xsl:variable>
	<xsl:variable name="name">
		<xsl:value-of select="string(@name)"/>
	</xsl:variable>
	<xsl:variable name="value">
		<xsl:value-of select="string(@value)"/>
	</xsl:variable>
	<xsl:call-template name="attributeOutput">
		<xsl:with-param name="id" select="$attributeId"/>
		<xsl:with-param name="name" select="$name"/>
		<xsl:with-param name="value" select="$value"/>
	</xsl:call-template>
</xsl:template>

<xsl:template name="attributeOutput">
	<xsl:param name="id"/>
	<xsl:param name="name"/>
	<xsl:param name="value"/>
	<xsl:text>(</xsl:text><xsl:value-of select="$id"/><xsl:text>|</xsl:text><xsl:value-of select="$name"/><xsl:text>|</xsl:text><xsl:value-of select="$value"/><xsl:text>)</xsl:text>
</xsl:template>
</xsl:stylesheet>