package familySpace;

import familySpace.FatherType;
import familySpace.MotherType;
import interfaces.CoupsMarteaux;
import interfaces.FabriqueGateaux;

@SuppressWarnings("all")
public abstract class Family {
  @SuppressWarnings("all")
  public interface Requires {
  }
  
  
  @SuppressWarnings("all")
  public interface Provides {
    /**
     * This can be called to access the provided port.
     * 
     */
    public CoupsMarteaux service();
  }
  
  
  @SuppressWarnings("all")
  public interface Component extends Family.Provides {
  }
  
  
  @SuppressWarnings("all")
  public interface Parts {
    /**
     * This can be called by the implementation to access the part and its provided ports.
     * It will be initialized after the required ports are initialized and before the provided ports are initialized.
     * 
     */
    public FatherType.Component father();
    
    /**
     * This can be called by the implementation to access the part and its provided ports.
     * It will be initialized after the required ports are initialized and before the provided ports are initialized.
     * 
     */
    public MotherType.Component mother();
  }
  
  
  @SuppressWarnings("all")
  public static class ComponentImpl implements Family.Component, Family.Parts {
    private final Family.Requires bridge;
    
    private final Family implementation;
    
    public void start() {
      assert this.father != null: "This is a bug.";
      ((FatherType.ComponentImpl) this.father).start();
      assert this.mother != null: "This is a bug.";
      ((MotherType.ComponentImpl) this.mother).start();
      this.implementation.start();
      this.implementation.started = true;
      
    }
    
    protected void initParts() {
      assert this.father == null: "This is a bug.";
      assert this.implem_father == null: "This is a bug.";
      this.implem_father = this.implementation.make_father();
      if (this.implem_father == null) {
      	throw new RuntimeException("make_father() in familySpace.Family should not return null.");
      }
      this.father = this.implem_father._newComponent(new BridgeImpl_father(), false);
      assert this.mother == null: "This is a bug.";
      assert this.implem_mother == null: "This is a bug.";
      this.implem_mother = this.implementation.make_mother();
      if (this.implem_mother == null) {
      	throw new RuntimeException("make_mother() in familySpace.Family should not return null.");
      }
      this.mother = this.implem_mother._newComponent(new BridgeImpl_mother(), false);
      
    }
    
    protected void initProvidedPorts() {
      
    }
    
    public ComponentImpl(final Family implem, final Family.Requires b, final boolean doInits) {
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
    
    public final CoupsMarteaux service() {
      return this.father.bricole();
    }
    
    private FatherType.Component father;
    
    private FatherType implem_father;
    
    @SuppressWarnings("all")
    private final class BridgeImpl_father implements FatherType.Requires {
      public final FabriqueGateaux cassecroute() {
        return Family.ComponentImpl.this.mother.faitplaiz();
      }
    }
    
    
    public final FatherType.Component father() {
      return this.father;
    }
    
    private MotherType.Component mother;
    
    private MotherType implem_mother;
    
    @SuppressWarnings("all")
    private final class BridgeImpl_mother implements MotherType.Requires {
    }
    
    
    public final MotherType.Component mother() {
      return this.mother;
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
  
  private Family.ComponentImpl selfComponent;
  
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
  protected Family.Provides provides() {
    assert this.selfComponent != null: "This is a bug.";
    if (!this.init) {
    	throw new RuntimeException("provides() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if provides() is needed to initialise the component.");
    }
    return this.selfComponent;
    
  }
  
  /**
   * This can be called by the implementation to access the required ports.
   * 
   */
  protected Family.Requires requires() {
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
  protected Family.Parts parts() {
    assert this.selfComponent != null: "This is a bug.";
    if (!this.init) {
    	throw new RuntimeException("parts() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if parts() is needed to initialise the component.");
    }
    return this.selfComponent;
    
  }
  
  /**
   * This should be overridden by the implementation to define how to create this sub-component.
   * This will be called once during the construction of the component to initialize this sub-component.
   * 
   */
  protected abstract FatherType make_father();
  
  /**
   * This should be overridden by the implementation to define how to create this sub-component.
   * This will be called once during the construction of the component to initialize this sub-component.
   * 
   */
  protected abstract MotherType make_mother();
  
  /**
   * Not meant to be used to manually instantiate components (except for testing).
   * 
   */
  public synchronized Family.Component _newComponent(final Family.Requires b, final boolean start) {
    if (this.init) {
    	throw new RuntimeException("This instance of Family has already been used to create a component, use another one.");
    }
    this.init = true;
    Family.ComponentImpl comp = new Family.ComponentImpl(this, b, true);
    if (start) {
    	comp.start();
    }
    return comp;
    
  }
  
  /**
   * Use to instantiate a component from this implementation.
   * 
   */
  public Family.Component newComponent() {
    return this._newComponent(new Family.Requires() {}, true);
  }
}
