package com.ecommerceJava.ecommerce.projections;

public interface UserDetailsProjection {

    String getUsername();
	String getPassword();
	Long getRoleId();
	String getAuthority();

}
