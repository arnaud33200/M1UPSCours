package ArnaudSpace;

import interfaces.InterfaceTypeArnaud1;
import interfaces.InterfaceTypeArnaud2;

@SuppressWarnings("all")
public abstract class Compopo {
  @SuppressWarnings("all")
  public interface Requires {
  }
  
  
  @SuppressWarnings("all")
  public interface Provides {
    /**
     * This can be called to access the provided port.
     * 
     */
    public InterfaceTypeArnaud1 fourniture1();
    
    /**
     * This can be called to access the provided port.
     * 
     */
    public InterfaceTypeArnaud2 fourniture2();
  }
  
  
  @SuppressWarnings("all")
  public interface Component extends Compopo.Provides {
  }
  
  
  @SuppressWarnings("all")
  public interface Parts {
  }
  
  
  @SuppressWarnings("all")
  public static class ComponentImpl implements Compopo.Component, Compopo.Parts {
    private final Compopo.Requires bridge;
    
    private final Compopo implementation;
    
    public void start() {
      this.implementation.start();
      this.implementation.started = true;
      
    }
    
    protected void initParts() {
      
    }
    
    protected void initProvidedPorts() {
      assert this.fourniture1 == null: "This is a bug.";
      this.fourniture1 = this.implementation.make_fourniture1();
      if (this.fourniture1 == null) {
      	throw new RuntimeException("make_fourniture1() in ArnaudSpace.Compopo should not return null.");
      }
      assert this.fourniture2 == null: "This is a bug.";
      this.fourniture2 = this.implementation.make_fourniture2();
      if (this.fourniture2 == null) {
      	throw new RuntimeException("make_fourniture2() in ArnaudSpace.Compopo should not return null.");
      }
      
    }
    
    public ComponentImpl(final Compopo implem, final Compopo.Requires b, final boolean doInits) {
      this.bridge = b;
      this.implementation = implem;
      
      assert implem.selfComponent == null: "This is a bug.";
      implem.selfComponent = this;
      
      // prevent them to be called twice if we are in
      // a specialized component: only the last of the
      // hierarchy will call them after everything is initialised
      if (doInits) {
      	initParts();
      	initProvidedPorts();
      }
      
    }
    
    private InterfaceTypeArnaud1 fourniture1;
    
    public final InterfaceTypeArnaud1 fourniture1() {
      return this.fourniture1;
    }
    
    private InterfaceTypeArnaud2 fourniture2;
    
    public final InterfaceTypeArnaud2 fourniture2() {
      return this.fourniture2;
    }
  }
  
  
  /**
   * Used to check that two components are not created from the same implementation,
   * that the component has been started to call requires(), provides() and parts()
   * and that the component is not started by hand.
   * 
   */
  private boolean init = false;;
  
  /**
   * Used to check that the component is not started by hand.
   */
  private boolean started = false;;
  
  private Compopo.ComponentImpl selfComponent;
  
  /**
   * Can be overridden by the implementation.
   * It will be called automatically after the component has been instantiated.
   * 
   */
  protected void start() {
    if (!this.init || this.started) {
    	throw new RuntimeException("start() should not be called by hand: to create a new component, use newComponent().");
    }
    
  }
  
  /**
   * This can be called by the implementation to access the provided ports.
   * 
   */
  protected Compopo.Provides provides() {
    assert this.selfComponent != null: "This is a bug.";
    if (!this.init) {
    	throw new RuntimeException("provides() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if provides() is needed to initialise the component.");
    }
    return this.selfComponent;
    
  }
  
  /**
   * This should be overridden by the implementation to define the provided port.
   * This will be called once during the construction of the component to initialize the port.
   * 
   */
  protected abstract InterfaceTypeArnaud1 make_fourniture1();
  
  /**
   * This should be overridden by the implementation to define the provided port.
   * This will be called once during the construction of the component to initialize the port.
   * 
   */
  protected abstract InterfaceTypeArnaud2 make_fourniture2();
  
  /**
   * This can be called by the implementation to access the required ports.
   * 
   */
  protected Compopo.Requires requires() {
    assert this.selfComponent != null: "This is a bug.";
    if (!this.init) {
    	throw new RuntimeException("requires() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if requires() is needed to initialise the component.");
    }
    return this.selfComponent.bridge;
    
  }
  
  /**
   * This can be called by the implementation to access the parts and their provided ports.
   * 
   */
  protected Compopo.Parts parts() {
    assert this.selfComponent != null: "This is a bug.";
    if (!this.init) {
    	throw new RuntimeException("parts() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if parts() is needed to initialise the component.");
    }
    return this.selfComponent;
    
  }
  
  /**
   * Not meant to be used to manually instantiate components (except for testing).
   * 
   */
  public synchronized Compopo.Component _newComponent(final Compopo.Requires b, final boolean start) {
    if (this.init) {
    	throw new RuntimeException("This instance of Compopo has already been used to create a component, use another one.");
    }
    this.init = true;
    Compopo.ComponentImpl comp = new Compopo.ComponentImpl(this, b, true);
    if (start) {
    	comp.start();
    }
    return comp;
    
  }
  
  /**
   * Use to instantiate a component from this implementation.
   * 
   */
  public Compopo.Component newComponent() {
    return this._newComponent(new Compopo.Requires() {}, true);
  }
}
