package com.hevodata.twitterads4j.internal.models4j;

public interface OEmbed extends TwitterResponse, java.io.Serializable {
    /**
     * The HTML required to display the resource. The HTML should have no padding or margins. Consumers may wish to load the HTML in an off-domain iframe to avoid XSS vulnerabilities. The markup should be valid XHTML 1.0 Basic.
     * @return The HTML required to display the resource.
     */
    String getHtml();

    /**
     * The name of the author/owner of the resource.
     * @return The name of the author/owner of the resource.
     */
    String getAuthorName();


// provier_url is always "http://twitter.com/"
//    String getProviderURL();

    /**
     * The url of the resource provider.<br>
     * The source URL of the image. Consumers should be able to insert this URL into an &lt;img&gt; element. Only HTTP and HTTPS URLs are valid.
     * @return The url of the resource provider.
     */
    String getURL();

// provider_name is always "Twitter"
//    String getProviderName();

    /**
     * The oEmbed version number.
     * @return The oEmbed version number.
     */
    String getVersion();

// type is always "rich"
//    String getType();

    /**
     * The suggested cache lifetime for this resource, in seconds. Consumers may choose to use this value or not.
     * @return The suggested cache lifetime for this resource, in seconds. Consumers may choose to use this value or not.
     */
    long getCacheAge();

    /**
     * A URL for the author/owner of the resource.
     * @return A URL for the author/owner of the resource.
     */
    String getAuthorURL();

    /**
     * The width in pixels of the image specified in the url parameter.
     * @return The width in pixels of the image specified in the url parameter.
     */
    int getWidth();
}
