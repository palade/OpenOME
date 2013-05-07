<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:exsl="http://exslt.org/common"
	extension-element-prefixes="exsl"
	xmlns:xmi="http://www.omg.org/XMI"
	xmlns:notation="http://www.eclipse.org/gmf/runtime/1.0.1/notation"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:edu.toronto.cs.openome_model="http:///edu/toronto/cs/openome_model.ecore">


	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:strip-space elements="children"/>
	<!-- Little Hack which prevents unwanted white spaces in output  -->
	<xsl:template match="text()" />
	
	<!-- Simple node type mapping  -->
	<xsl:template name="GetValidIstarmlNodeName">
		<xsl:param name="nodeName"/>
		<xsl:variable name="normalizedName">
			<xsl:call-template name="NormalizeOpenOMELinkName">
				<xsl:with-param name="name" select="$nodeName"/>
			</xsl:call-template>
		</xsl:variable>
		<xsl:choose>
			<xsl:when test="compare($normalizedName, 'container') = 0">
				<xsl:text>actor</xsl:text>
			</xsl:when>
			<xsl:when test="compare($normalizedName, 'intention') = 0">
				<xsl:text>ielement</xsl:text>
			</xsl:when>
			<xsl:when test="compare($normalizedName, 'dependency') = 0">
				<xsl:text>dependency</xsl:text>
			</xsl:when>
			<xsl:when test="compare($normalizedName, 'contribution') = 0">
				<xsl:text>ielementLink</xsl:text>
			</xsl:when>
			<xsl:when test="compare($normalizedName, 'decomposition') = 0">
				<xsl:text>ielementLink</xsl:text>
			</xsl:when>
			<xsl:when test="compare($normalizedName, 'association') = 0">
				<xsl:text>actorLink</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:text>undefined</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<!-- Simple decomposition/contribution value mapping -->
	<xsl:template name="GetValidIstarmlLinkValue">
		<xsl:param name="type"/>
		<xsl:choose>
			<xsl:when test="compare($type, 'someminus') = 0">
				<xsl:text>some-</xsl:text>
			</xsl:when>
			<xsl:when test="compare($type, 'someplus') = 0">
				<xsl:text>some+</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$type"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<!-- Simple actor association mapping -->
	<xsl:template name="GetValidIstarmlActorLinkType">
		<xsl:param name="type"/>
		<xsl:variable name="normalizedType">
			<xsl:call-template name="NormalizeOpenOMELinkValue">
				<xsl:with-param name="value" select="$type"/>
				<xsl:with-param name="unwantedSuffix">association</xsl:with-param>
			</xsl:call-template>
		</xsl:variable>
		<xsl:choose>
			<xsl:when test="compare($normalizedType, 'ins') = 0">
				<xsl:text>instance_of</xsl:text>
			</xsl:when>
			<xsl:when test="compare($normalizedType, 'isa') = 0">
				<xsl:text>is_a</xsl:text>
			</xsl:when>
			<xsl:when test="compare($normalizedType, 'ispartof') = 0">
				<xsl:text>is_part_of</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$normalizedType"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template name="GetDependencyElementIdByDependencyId">
		<xsl:param name="dependencyId"/>
		<xsl:param name="dependencyType"/>
		<xsl:choose>
			<xsl:when test="compare($dependencyType, 'depender') = 0">
				<xsl:value-of select="//dependencies[@xmi:id=$dependencyId]/@dependencyTo"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="//dependencies[@xmi:id=$dependencyId]/@dependencyFrom"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template name="GenerateDependencyElement">
		<xsl:param name="id"/>
		<xsl:param name="dependencyId"/>
		<xsl:param name="dependencyType"/>
		<xsl:variable name="dependencyElementName">
			<xsl:call-template name="GetValidIstarmlNodeName">
				<xsl:with-param name="nodeName" select="//*[@xmi:id=$id]/name()"/>
			</xsl:call-template>
		</xsl:variable>
		<xsl:element name="{$dependencyType}">
			<xsl:if test="compare($dependencyElementName, 'ielement') = 0">
				<xsl:attribute name="iref">
					<xsl:value-of select="$id"/>
				</xsl:attribute>
				<xsl:attribute name="aref">
					<xsl:value-of select="//intentions[@xmi:id=$id]/../@xmi:id"/>
				</xsl:attribute>
			</xsl:if>
			<xsl:if test="compare($dependencyElementName, 'actor') = 0">
				<xsl:attribute name="aref">
					<xsl:value-of select="$id"/>
				</xsl:attribute>
			</xsl:if>
			<xsl:call-template name="GenerateSpecialAttributesByNodeId">
				<xsl:with-param name="id" select="$dependencyId"/>
			</xsl:call-template>
			<xsl:call-template name="GenerateBasicGraphicPath"/>
		</xsl:element>
	</xsl:template>
	
	<!-- 
		This template splits the incoming id string, identifies
		depender/dependee and generates the appropriate elements
		recursively.
	-->
	<xsl:template name="GenerateDependencyElements">
		<xsl:param name="dependencyIds"/>
		<xsl:param name="dependencyType"/>
		<xsl:if test="not(empty($dependencyIds))">
			<xsl:choose>
				<xsl:when test="contains($dependencyIds, ' ')">
					<xsl:variable name="dependencyElementId">
						<xsl:call-template name="GetDependencyElementIdByDependencyId">
							<xsl:with-param name="dependencyId" select="substring-before($dependencyIds, ' ')"/>
							<xsl:with-param name="dependencyType" select="$dependencyType"/>
						</xsl:call-template>
					</xsl:variable>
					<xsl:call-template name="GenerateDependencyElement">
						<xsl:with-param name="id" select="$dependencyElementId"/>
						<xsl:with-param name="dependencyId" select="substring-before($dependencyIds, ' ')"/>
						<xsl:with-param name="dependencyType" select="$dependencyType"/>
					</xsl:call-template>
					<xsl:call-template name="GenerateDependencyElements">
						<xsl:with-param name="dependencyIds" select="substring-after($dependencyIds, ' ')"/>
						<xsl:with-param name="dependencyType" select="$dependencyType"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<xsl:variable name="dependencyElementId">
						<xsl:call-template name="GetDependencyElementIdByDependencyId">
							<xsl:with-param name="dependencyId" select="$dependencyIds"/>
							<xsl:with-param name="dependencyType" select="$dependencyType"/>
						</xsl:call-template>
					</xsl:variable>
					<xsl:call-template name="GenerateDependencyElement">
						<xsl:with-param name="id" select="$dependencyElementId"/>
						<xsl:with-param name="dependencyId" select="$dependencyIds"/>
						<xsl:with-param name="dependencyType" select="$dependencyType"/>
					</xsl:call-template>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:if>
	</xsl:template>
	
	<!-- Actor's rationale generation (using referential notation) -->
	<xsl:template name="GenerateActorsRationale">
		<xsl:param name="id"/>
		<xsl:param name="nodes"/>
		<xsl:for-each select="$nodes">
			<xsl:element name="ielementLink">
				<xsl:variable name="linkName">
					<xsl:call-template name="NormalizeOpenOMELinkName">
						<xsl:with-param name="name" select="name()"/>
					</xsl:call-template>
				</xsl:variable>
				<xsl:attribute name="type">
					<xsl:value-of select="$linkName"/>
				</xsl:attribute>
				<xsl:attribute name="value">
					<xsl:call-template name="GetValidIstarmlLinkValue">
						<xsl:with-param name="type">
							<xsl:call-template name="NormalizeOpenOMELinkValue">
								<xsl:with-param name="value" select="@xmi:type"/>
								<xsl:with-param name="unwantedSuffix" select="$linkName"/>
							</xsl:call-template>
						</xsl:with-param>
					</xsl:call-template>
				</xsl:attribute>
				<xsl:attribute name="iref">
					<xsl:value-of select="@target"/>
				</xsl:attribute>
				<xsl:call-template name="GenerateSpecialAttributesByNodeId">
					<xsl:with-param name="id" select="@xmi:id"/>
				</xsl:call-template>
				<xsl:call-template name="GenerateBasicGraphicPath"/>
			</xsl:element>
		</xsl:for-each>
	</xsl:template>
	
	<!-- Actor association generation (using referential notation) -->
	<xsl:template name="GenerateActorAssociations">
		<xsl:param name="actorId"/>
		<xsl:variable name="associations" select="//associations[@source=$actorId]"/>
		<xsl:if test="count($associations) > 0">
			<xsl:for-each select="$associations">
				<xsl:element name="actorLink">
					<xsl:attribute name="type">
						<xsl:call-template name="GetValidIstarmlActorLinkType">
							<xsl:with-param name="type" select="@xmi:type"/>
						</xsl:call-template>
					</xsl:attribute>
					<xsl:attribute name="aref">
						<xsl:value-of select="@target"/>
					</xsl:attribute>
					<xsl:call-template name="GenerateSpecialAttributesByNodeId">
						<xsl:with-param name="id" select="@xmi:id"/>
					</xsl:call-template>
					<xsl:call-template name="GenerateBasicGraphicPath"/>
				</xsl:element>
			</xsl:for-each>
		</xsl:if>
	</xsl:template>
	
	<!-- Generate iStarML root element -->
	<xsl:template match="/">
		<xsl:element name="istarml">
			<xsl:attribute name="version">
				<xsl:text>1.0</xsl:text>
			</xsl:attribute>
			<xsl:element name="diagram">
				<xsl:attribute name="name">
					<xsl:value-of select="//*[@type='openome_model']/@name"/>
				</xsl:attribute>
				<xsl:call-template name="GenerateSpecialAttributesByNodeId">
					<xsl:with-param name="id" select="//edu.toronto.cs.openome_model:Model/@xmi:id"/>
				</xsl:call-template>
				<xsl:call-template name="GenerateBasicGraphicDiagram"/>
				<xsl:apply-templates />
			</xsl:element>
		</xsl:element>
	</xsl:template>
	
	<!-- Actor's boundary transformation -->
	<xsl:template name="GenerateBoundary">
		<xsl:param name="node"/>
		<xsl:element name="boundary">
			<xsl:call-template name="GenerateBasicGraphicShape">
				<xsl:with-param name="node" select="$node"/>
			</xsl:call-template>
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>
	
	<!-- Actors transformation -->
	<xsl:template name="Actors" match="containers">
		<xsl:variable name="ActorType">
			<xsl:call-template name="NormalizeOpenOMEType">
				<xsl:with-param name="type" select="@xmi:type"/>
			</xsl:call-template>
		</xsl:variable>
		<xsl:variable name="id">
			<xsl:value-of select="string(@xmi:id)"/>
		</xsl:variable>
		<xsl:element name="actor">
			<xsl:if test="matches($ActorType, '^(agent|role|position)$')">
				<xsl:attribute name="type">
					<xsl:value-of select="$ActorType"/>
				</xsl:attribute>
			</xsl:if>
			<xsl:attribute name="id">
				<xsl:value-of select="$id"/>
			</xsl:attribute>
			<xsl:attribute name="name">
				<xsl:call-template name="NormalizeName">
					<xsl:with-param name="name" select="@name"/>
				</xsl:call-template>
			</xsl:attribute>
			<xsl:call-template name="GenerateSpecialAttributesByNodeId">
				<xsl:with-param name="id" select="$id"/>
			</xsl:call-template>
			<xsl:call-template name="GenerateBasicGraphicNode">
				<xsl:with-param name="node" select="//children[@element=$id]"/>
			</xsl:call-template>
			<xsl:call-template name="GenerateActorAssociations">
				<xsl:with-param name="actorId" select="$id"/>
			</xsl:call-template>
			<xsl:if test="count(child::*) > 0">
				<xsl:call-template name="GenerateBoundary">
					<xsl:with-param name="node" select="//children[@element=$id]"/>
				</xsl:call-template>
			</xsl:if>
		</xsl:element>
	</xsl:template>
	
	<!-- Intentional elements transformation -->
	<xsl:template name="Intentions" match="intentions">
		<xsl:variable name="IntentionType">
			<xsl:call-template name="NormalizeOpenOMEType">
				<xsl:with-param name="type" select="@xmi:type"/>
			</xsl:call-template>
		</xsl:variable>
		<xsl:variable name="id">
			<xsl:value-of select="string(@xmi:id)"/>
		</xsl:variable>
		<xsl:element name="ielement">
			<xsl:if test="matches($IntentionType, '^(goal|softgoal|task|resource)$')">
				<xsl:attribute name="type">
					<xsl:value-of select="$IntentionType"/>
				</xsl:attribute>
			</xsl:if>
			<xsl:attribute name="id">
				<xsl:value-of select="$id"/>
			</xsl:attribute>
			<xsl:attribute name="name">
				<xsl:call-template name="NormalizeName">
					<xsl:with-param name="name" select="@name"/>
				</xsl:call-template>
			</xsl:attribute>
			<xsl:call-template name="GenerateSpecialAttributesByNodeId">
				<xsl:with-param name="id" select="$id"/>
			</xsl:call-template>
			<xsl:call-template name="GenerateBasicGraphicNode">
				<xsl:with-param name="node" select="//children[@element=$id]"/>
			</xsl:call-template>
			<xsl:variable name="IntentionNodes" select="//decompositions[@source=$id]|//contributions[@source=$id]"/>
			<xsl:if test="count($IntentionNodes) > 0">
				<xsl:call-template name="GenerateActorsRationale">
					<xsl:with-param name="id" select="$id"></xsl:with-param>
					<xsl:with-param name="nodes" select="$IntentionNodes"></xsl:with-param>
				</xsl:call-template>
			</xsl:if>
			<!-- intention out of any boundary -->
			<xsl:if test="count(parent::containers) = 0">
				<xsl:if test="string-length(@dependencyTo) > 0">
					<xsl:element name="dependency">
						<xsl:call-template name="GenerateDependencyElements">
							<xsl:with-param name="dependencyIds" select="@dependencyTo"/>
							<xsl:with-param name="dependencyType">depender</xsl:with-param>
						</xsl:call-template>
						<xsl:if test="string-length(@dependencyFrom) > 0">
							<xsl:call-template name="GenerateDependencyElements">
								<xsl:with-param name="dependencyIds" select="@dependencyFrom"/>
								<xsl:with-param name="dependencyType">dependee</xsl:with-param>
							</xsl:call-template>
						</xsl:if>
					</xsl:element>
				</xsl:if>
			</xsl:if>
		</xsl:element>
	</xsl:template>




	<!-- Helper Functions -->
	<xsl:variable name="attributeFilter" 
			select="('xmi:id', 'xmi:type', 'xmi:version', 'xmi:*', 'name', 'source', 'target', 'dependencyFrom', 'dependencyTo', 'element', 'measurementUnit', 'type', 'decompositions', 
					 'parentDecompositions', 'contributesTo', 'contributesFrom', 'x', 'y', 'width', 'height', 'fontName', 'fontColor', 'fillColor', 'lineColor', 
					 'collapsed', 'points', 'description', 'lineWidth', 'transparency', 'smoothness', 'jumpLinkStatus', 'id')"/>

	<xsl:template name="GetAttribute">
		<xsl:param name="value"/>
		<xsl:param name="default"/>
		<xsl:choose>
			<xsl:when test="empty($value)">
				<xsl:value-of select="$default"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$value"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template name="NormalizeName">
		<xsl:param name="name"/>
		<xsl:variable name="forbiddenChars">'";</xsl:variable>
		<xsl:value-of select="translate($name, $forbiddenChars, '')"/>
	</xsl:template>

	<xsl:template name="NormalizeOpenOMEType">
		<xsl:param name="type"/>
		<xsl:value-of select="substring-after(lower-case($type), ':')"/>
	</xsl:template>

	<xsl:template name="NormalizeOpenOMELinkName">
		<xsl:param name="name"/>
		<xsl:choose>
			<xsl:when test="compare($name, 'dependencies') = 0">
				<xsl:text>dependency</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="substring(lower-case($name), 0, string-length($name))"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template name="GenerateSpecialAttributesByNodeId">
		<xsl:param name="id"/>
		<xsl:for-each select="(//*[@xmi:id=$id]/@*)">
			<xsl:if test="empty(index-of($attributeFilter, name(.)))">
				<xsl:attribute name="{name(.)}">
					<xsl:value-of select="."/>
				</xsl:attribute>
			</xsl:if>
		</xsl:for-each>
	</xsl:template>
	
	<xsl:template name="FilterSpecialAttributes">
		<xsl:param name="id"/>
		<xsl:for-each select="(//*[@xmi:id=$id]/@*)">
			<xsl:if test="not(empty(index-of($attributeFilter, name(.))))">
				<xsl:attribute name="{name(.)}">
					<xsl:value-of select="."/>
				</xsl:attribute>
			</xsl:if>
		</xsl:for-each>
	</xsl:template>
	
	<xsl:template name="GenerateSpecialAssertionsByNodeId">
		<xsl:param name="id"/>
		<xsl:variable name="nodeType">
			<xsl:call-template name="NormalizeOpenOMEType">
				<xsl:with-param name="type" select="name(//*[@xmi:id=$id])"/>
			</xsl:call-template>
		</xsl:variable>
		<xsl:variable name="attributeType">
			<xsl:choose>
				<xsl:when test="compare($nodeType, 'model') = 0">
					<xsl:text>modelattribute</xsl:text>
				</xsl:when>
				<xsl:otherwise>
					<xsl:text>attribute</xsl:text>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:for-each select="(//*[@xmi:id=$id]/@*)">
			<xsl:if test="empty(index-of($attributeFilter, name(.)))">
				<xsl:value-of select="$attributeType"/><xsl:text>('</xsl:text><xsl:value-of select="$id"/><xsl:text>', '</xsl:text><xsl:value-of select="name(.)"/><xsl:text>', '</xsl:text><xsl:value-of select="."/><xsl:text>').&#10;</xsl:text>
			</xsl:if>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="NormalizeOpenOMELinkValue">
		<xsl:param name="value"/>
		<xsl:param name="unwantedSuffix"/>
		<xsl:value-of select="replace(substring-after(lower-case($value), ':'), $unwantedSuffix, '')"/>
	</xsl:template>

	<xsl:template name="GetOpenOMENodeById">
		<xsl:param name="id"/>
		<xsl:value-of select="//*[@xmi:id=$id]"/>
	</xsl:template>

	<xsl:template name="GetOpenOMENodeNameById">
		<xsl:param name="id"/>
		<xsl:variable name="node">
			<xsl:call-template name="GetOpenOMENodeById">
				<xsl:with-param name="id" select="$id"/>
			</xsl:call-template>
		</xsl:variable>
		<xsl:value-of select="name($node)"/>
	</xsl:template>
	
	<xsl:template name="GetDefaultWidthByNodeId">
		<xsl:param name="id"/>
		<xsl:variable name="nodeName" select="name(//*[@xmi:id=$id])"/>
		<xsl:choose>
			<xsl:when test="compare($nodeName, 'containers') = 0">
				<xsl:text>100</xsl:text>
			</xsl:when>
			<xsl:when test="compare($nodeName, 'intentions') = 0">
				<xsl:text>120</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:text>0</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template name="GetDefaultHeightByNodeId">
		<xsl:param name="id"/>
		<xsl:variable name="nodeName" select="name(//*[@xmi:id=$id])"/>
		<xsl:choose>
			<xsl:when test="compare($nodeName, 'containers') = 0">
				<xsl:text>100</xsl:text>
			</xsl:when>
			<xsl:when test="compare($nodeName, 'intentions') = 0">
				<xsl:text>55</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:text>0</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>


	<!-- Layout 	 -->

	<xsl:template name="Dec2Hex">
	    <xsl:param name="dec"/>
	    <xsl:if test="$dec >= 16">
	        <xsl:call-template name="Dec2Hex">
	            <xsl:with-param name="dec" select="floor($dec div 16)"/>
	        </xsl:call-template>
	    </xsl:if>
	    <xsl:value-of select="substring('0123456789ABCDEF', ($dec mod 16) + 1, 1)"/>
	</xsl:template>
	
	<xsl:template name="GetRGBHexColor">
		<xsl:param name="value"/>
		<xsl:param name="default"/>
		<xsl:variable name="color">
			<xsl:call-template name="Dec2Hex">
				<xsl:with-param name="dec">
					<xsl:call-template name="GetAttribute">
						<xsl:with-param name="value" select="$value"/>
						<xsl:with-param name="default" select="$default"/>
					</xsl:call-template>
				</xsl:with-param>
			</xsl:call-template>
		</xsl:variable>
		<xsl:variable name="bgr" select="concat(substring('000000', 1, 6 - string-length($color)), $color)"/>
		<xsl:value-of select="concat(substring($bgr, 5, 2), substring($bgr, 3, 2), substring($bgr, 1, 2))"/>
	</xsl:template>

	<xsl:template name="GenerateBasicGraphicNode">
		<xsl:param name="node"/>
		<xsl:if test="not(empty($node))">
			<xsl:element name="graphic">
				<xsl:attribute name="content">
					<xsl:text>basic</xsl:text>
				</xsl:attribute>
				<xsl:attribute name="xpos">
					<xsl:call-template name="GetAttribute">
						<xsl:with-param name="value" select="$node/@x"/>
						<xsl:with-param name="default">0</xsl:with-param>
					</xsl:call-template>
				</xsl:attribute>
				<xsl:attribute name="ypos">
					<xsl:call-template name="GetAttribute">
						<xsl:with-param name="value" select="$node/@y"/>
						<xsl:with-param name="default">0</xsl:with-param>
					</xsl:call-template>
				</xsl:attribute>
				<xsl:attribute name="width">
					<xsl:call-template name="GetAttribute">
						<xsl:with-param name="value" select="$node/@width"/>
						<xsl:with-param name="default">
							<xsl:call-template name="GetDefaultWidthByNodeId">
								<xsl:with-param name="id" select="$node/@element"/>
							</xsl:call-template>
						</xsl:with-param>
					</xsl:call-template>
				</xsl:attribute>
				<xsl:attribute name="height">
					<xsl:call-template name="GetAttribute">
						<xsl:with-param name="value" select="$node/@height"/>
						<xsl:with-param name="default">
							<xsl:call-template name="GetDefaultHeightByNodeId">
								<xsl:with-param name="id" select="$node/@element"/>
							</xsl:call-template>
						</xsl:with-param>
					</xsl:call-template>
				</xsl:attribute>
				<xsl:attribute name="unit">
					<xsl:text>pt</xsl:text>
				</xsl:attribute>
				<xsl:attribute name="bgcolor">
					<xsl:call-template name="GetRGBHexColor">
						<xsl:with-param name="value" select="$node/@fillColor"/>
						<xsl:with-param name="default" as="xs:integer" select="16777215"/>
					</xsl:call-template>
				</xsl:attribute>
				<xsl:attribute name="fontfamily">
					<xsl:call-template name="GetAttribute">
						<xsl:with-param name="value" select="$node/@fontName"/>
						<xsl:with-param name="default">Segoe UI</xsl:with-param>
					</xsl:call-template>
				</xsl:attribute>
				<xsl:attribute name="fontcolor">
					<xsl:call-template name="GetRGBHexColor">
						<xsl:with-param name="value" select="$node/@fontColor"/>
						<xsl:with-param name="default" as="xs:integer" select="0"/>
					</xsl:call-template>
				</xsl:attribute>
				<xsl:attribute name="fontsize">
					<xsl:call-template name="GetAttribute">
						<xsl:with-param name="value" select="$node/@fontSize"/>
						<xsl:with-param name="default">12</xsl:with-param>
					</xsl:call-template>
				</xsl:attribute>
			</xsl:element>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="GenerateBasicGraphicPath">
		<xsl:param name="node"/>
		<xsl:if test="not(empty($node))">
			<xsl:element name="graphic">
				<xsl:attribute name="content">
					<xsl:text>SVG</xsl:text>
				</xsl:attribute>
			</xsl:element>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="GenerateBasicGraphicShape">
		<xsl:param name="node"/>
		<xsl:if test="not(empty($node))">
			<xsl:element name="graphic">
				<xsl:attribute name="content">
					<xsl:text>basic</xsl:text>
				</xsl:attribute>
				<xsl:attribute name="xpos">
					<xsl:call-template name="GetAttribute">
						<xsl:with-param name="value" select="$node/layoutConstraint/@x"/>
						<xsl:with-param name="default">0</xsl:with-param>
					</xsl:call-template>
				</xsl:attribute>
				<xsl:attribute name="ypos">
					<xsl:call-template name="GetAttribute">
						<xsl:with-param name="value" select="$node/layoutConstraint/@y"/>
						<xsl:with-param name="default">0</xsl:with-param>
					</xsl:call-template>
				</xsl:attribute>
				<xsl:attribute name="width">
					<xsl:call-template name="GetAttribute">
						<xsl:with-param name="value" select="$node/layoutConstraint/@width"/>
						<xsl:with-param name="default">0</xsl:with-param>
					</xsl:call-template>
				</xsl:attribute>
				<xsl:attribute name="height">
					<xsl:call-template name="GetAttribute">
						<xsl:with-param name="value" select="$node/layoutConstraint/@height"/>
						<xsl:with-param name="default">0</xsl:with-param>
					</xsl:call-template>
				</xsl:attribute>
				<xsl:attribute name="shape">
					<xsl:text>ellipse</xsl:text>
				</xsl:attribute>
				<xsl:attribute name="unit">
					<xsl:text>pt</xsl:text>
				</xsl:attribute>
				<xsl:attribute name="bgcolor">
					<xsl:call-template name="GetRGBHexColor">
						<xsl:with-param name="value" select="$node/@fillColor"/>
						<xsl:with-param name="default" as="xs:integer" select="16777215"/>
					</xsl:call-template>
				</xsl:attribute>
				<xsl:attribute name="fontfamily">
					<xsl:call-template name="GetAttribute">
						<xsl:with-param name="value" select="$node/@fontName"/>
						<xsl:with-param name="default"></xsl:with-param>
					</xsl:call-template>
				</xsl:attribute>
				<xsl:attribute name="fontcolor">
					<xsl:call-template name="GetRGBHexColor">
						<xsl:with-param name="value" select="$node/@fontColor"/>
						<xsl:with-param name="default" as="xs:integer" select="0"/>
					</xsl:call-template>
				</xsl:attribute>
				<xsl:attribute name="fontsize">
					<xsl:call-template name="GetAttribute">
						<xsl:with-param name="value" select="$node/@fontSize"/>
						<xsl:with-param name="default">12</xsl:with-param>
					</xsl:call-template>
				</xsl:attribute>
			</xsl:element>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="GenerateBasicGraphicDiagram">
		<xsl:element name="graphic">
			<xsl:attribute name="content">
				<xsl:text>SVG</xsl:text>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>




</xsl:stylesheet>