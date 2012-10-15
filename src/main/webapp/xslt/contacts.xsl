<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <head>
                <title>Contacts</title>
                <style>
                    a.edit-link, a.delete-link {
                        font-size: 0.8em;
                        color: gray;
                        padding-left: 7px;
                    }
                    p {
                        margin-bottom: 0;
                    }
                    label {
                        color: gray;
                        padding-right: 5px;
                    }
                </style>
            </head>
            <body>
                <h3>Contacts</h3>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="contacts">
        <xsl:apply-templates select="contact"/>
        <p><a href="create">Create Contact</a></p>
    </xsl:template>

    <xsl:template match="contact">
        <p>
            <xsl:call-template name="full_name">
                <xsl:with-param name="id" select="@id"/>
            </xsl:call-template>
            <br/>
            <xsl:apply-templates select="phone"/>
            <xsl:call-template name="delete">
                <xsl:with-param name="id" select="@id" />
            </xsl:call-template>
        </p>
    </xsl:template>

    <xsl:template match="phone">
        <xsl:variable name="id" select="../@id"/>
        <label>Phone:</label>
        <xsl:value-of select="."/>
        <a class="edit-link" href="edit_phone?id={$id}">(Edit phone)</a>
    </xsl:template>

    <xsl:template name="full_name">
        <xsl:param name="id"/>
        <label>Name:</label>
        <xsl:value-of select="@first_name"/>&#160;
        <xsl:value-of select="@last_name"/>
        <a class="edit-link" href="edit_first_name?id={$id}">(Edit)</a>
    </xsl:template>

    <xsl:template name="delete">
        <xsl:param name="id"/>
        <form action="delete">
            <input type="hidden" name="id" value="{$id}"/>
            <a href="#" class="delete-link" onclick="this.parentNode.submit(); return false;">Delete</a>
        </form>
    </xsl:template>
</xsl:stylesheet>