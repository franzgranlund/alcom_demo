package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Humanoid extends Model {
    @Id
    public Long id;

    @NotNull
    public String name;

    @NotNull
    public Integer age;

    public static Model.Finder<Long,Humanoid> find = new Model.Finder<>(
            Long.class, Humanoid.class
    );
}