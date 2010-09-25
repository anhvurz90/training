Append this config to …\tomcat\conf\jaas.conf


gatein-domain-trdemo {
  org.exoplatform.web.security.PortalLoginModule required;
  org.exoplatform.services.security.jaas.SharedStateLoginModule required;
  org.exoplatform.services.security.j2ee.TomcatLoginModule required; 
  
};
