/*
 * Copyright (c) 2002, 2004 Gargoyle Software Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. The end-user documentation included with the redistribution, if any, must
 *    include the following acknowledgment:
 *
 *       "This product includes software developed by Gargoyle Software Inc.
 *        (http://www.GargoyleSoftware.com/)."
 *
 *    Alternately, this acknowledgment may appear in the software itself, if
 *    and wherever such third-party acknowledgments normally appear.
 * 4. The name "Gargoyle Software" must not be used to endorse or promote
 *    products derived from this software without prior written permission.
 *    For written permission, please contact info@GargoyleSoftware.com.
 * 5. Products derived from this software may not be called "HtmlUnit", nor may
 *    "HtmlUnit" appear in their name, without prior written permission of
 *    Gargoyle Software Inc.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL GARGOYLE
 * SOFTWARE INC. OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.gargoylesoftware.htmlunit.jelly;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;

/**
 * Jelly tag "assertTitle"
 *
 * @version  $Revision$
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 */
public class AssertTitleTag extends HtmlUnitTagSupport {
    private String expectedText_;
    private String startsWithText_;

    /**
     * Process the tag.
     * @param xmlOutput The xml output
     * @throws JellyTagException If a problem occurs
     */
    public void doTag(XMLOutput xmlOutput) throws JellyTagException {
        invokeBody(xmlOutput);
        if( expectedText_ == null && startsWithText_ == null ) {
            throw new JellyTagException("One of 'text' or 'startsWith' must be specified");
        }

        final String actualText = getHtmlPage().getTitleText();
        if( expectedText_ != null && actualText.equals(expectedText_) == false ) {
            throw new JellyTagException("Expected text ["+expectedText_+"] but got ["+actualText+"] instead");
        }

        if( startsWithText_ != null && actualText.startsWith(startsWithText_) == false ) {
            throw new JellyTagException("Expected text to start with ["
                +startsWithText_+"] but got ["+actualText+"] instead");
        }
    }


    /**
     * Callback from Jelly to set the value of the text attribute.
     * @param text The new value.
     */
    public void setText( final String text ) {
        expectedText_ = text;
    }


    /**
     * Set the startsWith attribute
     * @param text the new value
     */
    public void setStartsWith( final String text ) {
        startsWithText_ = text;
    }
}

