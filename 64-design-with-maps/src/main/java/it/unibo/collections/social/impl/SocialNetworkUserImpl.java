/**
 * 
 */
package it.unibo.collections.social.impl;

import it.unibo.collections.social.api.SocialNetworkUser;
import it.unibo.collections.social.api.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * This will be an implementation of
 * {@link SocialNetworkUser}:
 * 1) complete the definition of the methods by following the suggestions
 * included in the comments below.
 * 
 * @param <U>
 *            Specific {@link User} type
 */
public final class SocialNetworkUserImpl<U extends User> extends UserImpl implements SocialNetworkUser<U> {

    /*
     *
     * [FIELDS]
     *
     * Define any necessary field
     *
     * In order to save the people followed by a user organized in groups, adopt
     * a generic-type Map: think of what type of keys and values would best suit the
     * requirements
     */
    private final Map<String, Set<U>> friends = new HashMap<>();

    /*
     * [CONSTRUCTORS]
     *
     * 1) Complete the definition of the constructor below, for building a user
     * participating in a social network, with 4 parameters, initializing:
     *
     * - firstName
     * - lastName
     * - username
     * - age and every other necessary field
     */
    /**
     * Builds a user participating in a social network.
     *
     * @param name
     *                the user firstname
     * @param surname
     *                the user lastname
     * @param userAge
     *                user's age
     * @param user
     *                alias of the user, i.e. the way a user is identified on an
     *                application
     */
    public SocialNetworkUserImpl(final String name, final String surname, final String user, final int userAge) {
        super(name, surname, user, userAge);
    }

    /*
     * 2) Define a further constructor where the age defaults to -1
     */
    public SocialNetworkUserImpl(String name, String surname, String user) {
        super(name, surname, user);
    }

    /*
     * [METHODS]
     *
     * Implements the methods below
     */
    @Override
    public boolean addFollowedUser(final String circle, final U user) {
        if (!groupExists(circle)) {
            createNewGroup(circle);
        }
        return getGroup(circle).add(user);
    }

    /**
     *
     * [NOTE] If no group with groupName exists yet, this implementation must
     * return an empty Collection.
     */
    @Override
    public Collection<U> getFollowedUsersInGroup(final String groupName) {
        final Collection<U> followedUsers = new HashSet<>();
        if (groupExists(groupName)) {
            for (U user : getGroup(groupName)) {
                followedUsers.add(user);
            }
        }
        return followedUsers;
    }

    @Override
    public List<U> getFollowedUsers() {
        List<U> followedUsers = new ArrayList<>();
        for (String group : this.friends.keySet()) {
            followedUsers.addAll(getFollowedUsersInGroup(group));
        }
        return followedUsers;
    }

    private Set<U> getGroup(final String group) {
        return this.friends.get(group);
    }

    private boolean groupExists(final String groupName) {
        return this.friends.containsKey(groupName);
    }

    private void createNewGroup(final String groupName) {
        this.friends.put(groupName, new HashSet<>());
    }
}
