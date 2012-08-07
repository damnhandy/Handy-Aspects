# Handy Aspects Library

Handy Aspects is a small project containing a set of reusable aspects that can be added to projects to add new bahaviors to existing classes with minimal modification. The aspects currently included in the project are as follows:

 * *JavaBean Aspect* – Allows you to register `PropertyChangeListeners` to POJOs that would not otherwise be observable.
 * *SyncModel Aspect* – A transparent threading solution for non-freezing Swing applications. The aspect still needs more testing, but works quite well.
Download

You can download the latest release, which includes the sources, documentation, and demo sources. While the orginal aspect used JBoss AOP, the aspects hosted here now use AspectJ and the JBoss AOP verison will no longer be maintained.

## Documentation

The project also includes demo sources that help you get started. Additional documentation is pending.

## Acknowledgments

The [JavaBean Aspect](http://damnhandy.com/javabean-aspect/) was derived from an earlier proof of concept which was originally inspired by the [GOFObservable](https://community.jboss.org/wiki/GOFObservable) aspect, which is part of the JBoss AOP Aspect Library.

The SyncModel Aspect was inspired by the [Spin project](http://spin.sourceforge.net) and contains an adapted version of the Dispatcher code. This aspect is currently a proof of concept and may be removed or enhanced in a future release.