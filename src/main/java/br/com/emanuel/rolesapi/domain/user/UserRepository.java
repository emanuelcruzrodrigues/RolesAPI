package br.com.emanuel.rolesapi.domain.user;

public interface UserRepository {

	User findById(String userId);

}
