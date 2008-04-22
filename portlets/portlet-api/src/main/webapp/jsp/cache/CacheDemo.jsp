<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects />
<h1><center>Portlets Cache</center></h1>

<p>
<div style="margin: 10px;border: 1px solid black; background-color: lightgrey">
This fragment was generated at <strong><%=new java.text.SimpleDateFormat("k:m:s").format(new java.util.Date().getTime()) %></strong>.<br/>
Cache was activated for <strong><%=(String)renderRequest.getAttribute("expiracy") %></strong> seconds.<br/>
<portlet:renderURL var="render"/>
Any further call to <a href="<%=render %>" style="color: blue;text-decoration: underline"><code>render()</code></a> will be intercepted by the container to render the cached content until it expires.<br/>
</div>
<br/>

</p>


<h2>Expiration</h2>
<p> 
<portlet:actionURL var="expire">
  <portlet:param name="action" value="expire"/>
</portlet:actionURL> 
You can use <code>CacheControl.setExpirationTime(int)</code>  to control cache expiration <br/>
<form action="<%=expire %>" method="post">
Set expiration in seconds : <input type="text" name="expiration"/><input type="submit"/></form>
</p>
<h2>Scope</h2>
<p>
<portlet:actionURL var="publicScope">
  <portlet:param name="action" value="scope"/>
  <portlet:param name="scope" value="public"/>
</portlet:actionURL>
<portlet:actionURL var="privateScope">
  <portlet:param name="action" value="scope"/>
  <portlet:param name="scope" value="private"/>
</portlet:actionURL>  
Cache can have <a href="<%=privateScope %>" style="color: blue;text-decoration: underline"><code>PRIVATE_SCOPE</code></a> in order to keep the cache private to the user.<br/> 
Cache can also be shared across users by using <a href="<%=publicScope %>" style="color: blue;text-decoration: underline"><code>PUBLIC_SCOPE</code></a>.
</p>

<h2>Cache validation</h2>
<p>
When cache expiration is not enough, you can provide your own cache validation logic.<br/>
You can use a token based cache validation with <code>CacheControl.setEtag(String)</code> and <code>CacheControl.getEtag()</code>.<br/>
The container will pass the etag to your request and you tell him to use cached content with <code>CacheControl.setUseCachedContent(true)</code><br/>

<portlet:actionURL var="validEtag">
<portlet:param name="validity" value="valid"/>
</portlet:actionURL>
<portlet:actionURL var="invalidEtag">
<portlet:param name="validity" value="invalid"/>
</portlet:actionURL>
Try this on a <a href="<%=validEtag %>" style="color: blue;text-decoration: underline">valid etag</a> and on 
<a href="<%=invalidEtag %>" style="color: blue;text-decoration: underline">an invalid etag</a>.

</p>