package lj.vgm.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class VoidConduitModel extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer Left;
    ModelRenderer Top;
    ModelRenderer Front;
    ModelRenderer Right;
    ModelRenderer Back;
  
  public VoidConduitModel()
  {
    textureWidth = 64;
    textureHeight = 32;
    
    
      Base = new ModelRenderer(this, 40, 0);
      Base.addBox(0F, 0F, 0F, 6, 11, 6);
      Base.setRotationPoint(-3F, 13F, -3F);
      Base.setTextureSize(64, 32);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Base.mirror = false;
      Left = new ModelRenderer(this, 0, 20);
      Left.addBox(0F, 0F, 0F, 5, 6, 6);
      Left.setRotationPoint(3F, 13F, -3F);
      Left.setTextureSize(64, 32);
      Left.mirror = true;
      setRotation(Left, 0F, 0F, 0F);
      Top = new ModelRenderer(this, 0, 20);
      Top.addBox(0F, 0F, 0F, 5, 6, 6);
      Top.setRotationPoint(-3F, 13F, -3F);
      Top.setTextureSize(64, 32);
      Top.mirror = true;
      setRotation(Top, 0F, 0F, -1.570796F);
      Front = new ModelRenderer(this, 0, 20);
      Front.addBox(0F, 0F, 0F, 5, 6, 6);
      Front.setRotationPoint(-3F, 13F, -3F);
      Front.setTextureSize(64, 32);
      Front.mirror = true;
      setRotation(Front, 0F, 1.570796F, 0F);
      Right = new ModelRenderer(this, 0, 20);
      Right.addBox(0F, 0F, 0F, 5, 6, 6);
      Right.setRotationPoint(-3F, 13F, 3F);
      Right.setTextureSize(64, 32);
      Right.mirror = true;
      setRotation(Right, 0F, 3.141593F, 0F);
      Back = new ModelRenderer(this, 0, 20);
      Back.addBox(0F, 0F, 0F, 5, 6, 6);
      Back.setRotationPoint(3F, 13F, 3F);
      Back.setTextureSize(64, 32);
      Back.mirror = true;
      setRotation(Back, 0F, -1.570796F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5,
          boolean base, boolean top, boolean front, boolean back, boolean right, boolean left)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    if (base)
    Base.render(f5);
    if (left)
    Left.render(f5);
    if (right)
    Top.render(f5);
    if (front)
    Front.render(f5);
    if (right)
    Right.render(f5);
    if (back)
    Back.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
