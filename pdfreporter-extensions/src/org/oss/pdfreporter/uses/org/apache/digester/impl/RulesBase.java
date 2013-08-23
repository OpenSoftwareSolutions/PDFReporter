package org.oss.pdfreporter.uses.org.apache.digester.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.oss.pdfreporter.uses.org.apache.digester.IDigester;
import org.oss.pdfreporter.uses.org.apache.digester.IRule;
import org.oss.pdfreporter.uses.org.apache.digester.IRules;


public class RulesBase implements IRules {
    /**
     * The set of registered Rule instances, keyed by the matching pattern.
     * Each value is a List containing the Rules for that pattern, in the
     * order that they were orginally registered.
     */
    protected HashMap<String, List<IRule>> cache = new HashMap<String, List<IRule>>();


    /**
     * The Digester instance with which this Rules instance is associated.
     */
    protected IDigester digester = null;


    /**
     * The namespace URI for which subsequently added <code>Rule</code>
     * objects are relevant, or <code>null</code> for matching independent
     * of namespaces.
     */
    protected String namespaceURI = null;


    /**
     * The set of registered Rule instances, in the order that they were
     * originally registered.
     */
    protected ArrayList<IRule> rules = new ArrayList<IRule>();


	@Override
	public IDigester getDigester() {
		return digester;
	}

	@Override
	public void setDigester(IDigester digester) {
        this.digester = digester;
        for (IRule rule : rules) {
            rule.setDigester(digester);
        }
	}

	@Override
	public String getNamespaceURI() {
        return (this.namespaceURI);
	}

	@Override
	public void setNamespaceURI(String namespaceURI) {
        this.namespaceURI = namespaceURI;
	}

	@Override
	public void add(String pattern, IRule rule) {
	       // to help users who accidently add '/' to the end of their patterns
        int patternLength = pattern.length();
        if (patternLength>1 && pattern.endsWith("/")) {
            pattern = pattern.substring(0, patternLength-1);
        }
        
        
        List<IRule> list = cache.get(pattern);
        if (list == null) {
            list = new ArrayList<IRule>();
            cache.put(pattern, list);
        }
        list.add(rule);
        rules.add(rule);
        if (this.digester != null) {
            rule.setDigester(this.digester);
        }
        if (this.namespaceURI != null) {
            rule.setNamespaceURI(this.namespaceURI);
        }
	}

	@Override
	public void clear() {
        cache.clear();
        rules.clear();
	}

	@Override
	@Deprecated
	public List<IRule> match(String pattern) {
	       return (match(null, pattern));
	}

	@Override
	public List<IRule> match(String namespaceURI, String pattern) {
        // List rulesList = (List) this.cache.get(pattern);
        List<IRule> rulesList = lookup(namespaceURI, pattern);
        if ((rulesList == null) || (rulesList.size() < 1)) {
            // Find the longest key, ie more discriminant
            String longKey = "";
            for (String key : cache.keySet()) {
                if (key.startsWith("*/")) {
                    if (pattern.equals(key.substring(2)) ||
                        pattern.endsWith(key.substring(1))) {
                        if (key.length() > longKey.length()) {
                            // rulesList = (List) this.cache.get(key);
                            rulesList = lookup(namespaceURI, key);
                            longKey = key;
                        }
                    }
                }
            }
        }
        if (rulesList == null) {
            rulesList = new ArrayList<IRule>();
        }
        return (rulesList);
	}

	@Override
	public List<IRule> rules() {
	       return (this.rules);
	}

	
    /**
     * Return a List of Rule instances for the specified pattern that also
     * match the specified namespace URI (if any).  If there are no such
     * rules, return <code>null</code>.
     *
     * @param namespaceURI Namespace URI to match, or <code>null</code> to
     *  select matching rules regardless of namespace URI
     * @param pattern Pattern to be matched
     */
    private List<IRule> lookup(String namespaceURI, String pattern) {

        // Optimize when no namespace URI is specified
        List<IRule> list = this.cache.get(pattern);
        if (list == null) {
            return (null);
        }
        if ((namespaceURI == null) || (namespaceURI.length() == 0)) {
            return (list);
        }

        // Select only Rules that match on the specified namespace URI
        ArrayList<IRule> results = new ArrayList<IRule>();
        for (IRule item : list) {
            if ((namespaceURI.equals(item.getNamespaceURI())) ||
                    (item.getNamespaceURI() == null)) {
                results.add(item);
            }
        }
        return (results);

    }
	
}
