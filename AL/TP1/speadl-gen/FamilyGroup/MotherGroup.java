package FamilyGroup;

import FamilyGroup.GroupConnect;
import familySpace.MotherType;
import interfaces.FabriqueGateaux;

@SuppressWarnings("all")
public abstract class MotherGroup {
  @SuppressWarnings("all")
  public interface Requires {
  }
  
  
  @SuppressWarnings("all")
  public interface Provides {
    /**
     * This can be called to access the provided port.
     * 
     */
    public FabriqueGateaux cassecroute();
  }
  
  
  @SuppressWarnings("all")
  public interface Component extends MotherGroup.Provides {
  }
  
  
  @SuppressWarnings("all")
  public interface Parts {
    /**
     * This can be called by the implementation to access the part and its provided ports.
     * It will be initialized after the required ports are initialized and before the provided ports are initialized.
     * 
     */
    public GroupConnect.Component connecter();
    
    /**
     * This can be called by the implementation to access the part and its provided ports.
     * It will be initialized after the required ports are initialized and before the provided ports are initialized.
     * 
     */
    public MotherType.Component m1();
    
    /**
     * This can be called by the implementation to access the part and its provided ports.
     * It will be initialized after the required ports are initialized and before the provided ports are initialized.
     * 
     */
    public MotherType.Component m2();
    
    /**
     * This can be called by the implementation to access the part and its provided ports.
     * It will be initialized after the required ports are initialized and before the provided ports are initialized.
     * 
     */
    public MotherType.Component m3();
  }
  
  
  @SuppressWarnings("all")
  public static class ComponentImpl implements MotherGroup.Component, MotherGroup.Parts {
    private final MotherGroup.Requires bridge;
    
    private final MotherGroup implementation;
    
    public void start() {
      assert this.connecter != null: "This is a bug.";
      ((GroupConnect.ComponentImpl) this.connecter).start();
      assert this.m1 != null: "This is a bug.";
      ((MotherType.ComponentImpl) this.m1).start();
      assert this.m2 != null: "This is a bug.";
      ((MotherType.ComponentImpl) this.m2).start();
      assert this.m3 != null: "This is a bug.";
      ((MotherType.ComponentImpl) this.m3).start();
      this.implementation.start();
      this.implementation.started = true;
      
    }
    
    protected void initParts() {
      assert this.connecter == null: "This is a bug.";
      assert this.implem_connecter == null: "This is a bug.";
      this.implem_connecter = this.implementation.make_connecter();
      if (this.implem_connecter == null) {
      	throw new RuntimeException("make_connecter() in FamilyGroup.MotherGroup should not return null.");
      }
      this.connecter = this.implem_connecter._newComponent(new BridgeImpl_connecter(), false);
      assert this.m1 == null: "This is a bug.";
      assert this.implem_m1 == null: "This is a bug.";
      this.implem_m1 = this.implementation.make_m1();
      if (this.implem_m1 == null) {
      	throw new RuntimeException("make_m1() in FamilyGroup.MotherGroup should not return null.");
      }
      this.m1 = this.implem_m1._newComponent(new BridgeImpl_m1(), false);
      assert this.m2 == null: "This is a bug.";
      assert this.implem_m2 == null: "This is a bug.";
      this.implem_m2 = this.implementation.make_m2();
      if (this.implem_m2 == null) {
      	throw new RuntimeException("make_m2() in FamilyGroup.MotherGroup should not return null.");
      }
      this.m2 = this.implem_m2._newComponent(new BridgeImpl_m2(), false);
      assert this.m3 == null: "This is a bug.";
      assert this.implem_m3 == null: "This is a bug.";
      this.implem_m3 = this.implementation.make_m3();
      if (this.implem_m3 == null) {
      	throw new RuntimeException("make_m3() in FamilyGroup.MotherGroup should not return null.");
      }
      this.m3 = this.implem_m3._newComponent(new BridgeImpl_m3(), false);
      
    }
    
    protected void initProvidedPorts() {
      
    }
    
    public ComponentImpl(final MotherGroup implem, final MotherGroup.Requires b, final boolean doInits) {
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
    
    public final FabriqueGateaux cassecroute() {
      return this.connecter.cassecroute();
    }
    
    private GroupConnect.Component connecter;
    
    private GroupConnect implem_connecter;
    
    @SuppressWarnings("all")
    private final class BridgeImpl_connecter implements GroupConnect.Requires {
      public final FabriqueGateaux mother1() {
        return MotherGroup.ComponentImpl.this.m1.faitplaiz();
      }
      
      public final FabriqueGateaux mother2() {
        return MotherGroup.ComponentImpl.this.m2.faitplaiz();
      }
      
      public final FabriqueGateaux mother3() {
        return MotherGroup.ComponentImpl.this.m3.faitplaiz();
      }
    }
    
    
    public final GroupConnect.Component connecter() {
      return this.connecter;
    }
    
    private MotherType.Component m1;
    
    private MotherType implem_m1;
    
    @SuppressWarnings("all")
    private final class BridgeImpl_m1 implements MotherType.Requires {
    }
    
    
    public final MotherType.Component m1() {
      return this.m1;
    }
    
    private MotherType.Component m2;
    
    private MotherType implem_m2;
    
    @SuppressWarnings("all")
    private final class BridgeImpl_m2 implements MotherType.Requires {
    }
    
    
    public final MotherType.Component m2() {
      return this.m2;
    }
    
    private MotherType.Component m3;
    
    private MotherType implem_m3;
    
    @SuppressWarnings("all")
    private final class BridgeImpl_m3 implements MotherType.Requires {
    }
    
    
    public final MotherType.Component m3() {
      return this.m3;
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
  
  private MotherGroup.ComponentImpl selfComponent;
  
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
  protected MotherGroup.Provides provides() {
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
  protected MotherGroup.Requires requires() {
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
  protected MotherGroup.Parts parts() {
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
  protected abstract GroupConnect make_connecter();
  
  /**
   * This should be overridden by the implementation to define how to create this sub-component.
   * This will be called once during the construction of the component to initialize this sub-component.
   * 
   */
  protected abstract MotherType make_m1();
  
  /**
   * This should be overridden by the implementation to define how to create this sub-component.
   * This will be called once during the construction of the component to initialize this sub-component.
   * 
   */
  protected abstract MotherType make_m2();
  
  /**
   * This should be overridden by the implementation to define how to create this sub-component.
   * This will be called once during the construction of the component to initialize this sub-component.
   * 
   */
  protected abstract MotherType make_m3();
  
  /**
   * Not meant to be used to manually instantiate components (except for testing).
   * 
   */
  public synchronized MotherGroup.Component _newComponent(final MotherGroup.Requires b, final boolean start) {
    if (this.init) {
    	throw new RuntimeException("This instance of MotherGroup has already been used to create a component, use another one.");
    }
    this.init = true;
    MotherGroup.ComponentImpl comp = new MotherGroup.ComponentImpl(this, b, true);
    if (start) {
    	comp.start();
    }
    return comp;
    
  }
  
  /**
   * Use to instantiate a component from this implementation.
   * 
   */
  public MotherGroup.Component newComponent() {
    return this._newComponent(new MotherGroup.Requires() {}, true);
  }
}
